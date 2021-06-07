package com.rms.rms.service.implementation;

import com.rms.rms.dto.order.OrderCreateDto;
import com.rms.rms.dto.order.OrderResponseDto;
import com.rms.rms.dto.order.UpdateStatusDto;
import com.rms.rms.entity.Order;
import com.rms.rms.entity.OrderMenuItem;
import com.rms.rms.entity.embedded.OrderMenuItemId;
import com.rms.rms.enums.Status;
import com.rms.rms.exception.OrderException;
import com.rms.rms.filters.OrderFilter;
import com.rms.rms.repository.OrderMenuItemRepository;
import com.rms.rms.repository.OrderRepository;
import com.rms.rms.repository.UserRepository;
import com.rms.rms.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class OrderServiceImpl extends BaseServiceImpl<OrderCreateDto, UpdateStatusDto, OrderResponseDto, Order> implements OrderService {

    private final OrderMenuItemRepository orderMenuItemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Override
    public OrderResponseDto save(OrderCreateDto order) {
        Order orderEntity = baseMapper.createDtoToEntity(order);

        orderEntity.setCostumer(userRepository.getOne(3L));

        List<Integer> availableDeliveryGuys = userRepository.findAllAvailableDeliveryGuys();

        if(!availableDeliveryGuys.isEmpty()) {
            int randomIndex = new Random().nextInt(availableDeliveryGuys.size());
            orderEntity.setDeliveryGuy(userRepository.getOne((long) availableDeliveryGuys.get(randomIndex)));
        } else {
            orderEntity.setDeliveryGuy(userRepository.findDeliveryGuyWithClosestDeliveryTime());
        }
        System.out.println(availableDeliveryGuys.size());

        orderEntity.setTotalCalories(
          orderEntity.getOrderMenuItems().stream()
                  .map(val -> val.getMenuItem().getCalories())
                  .reduce(0.0, Double::sum)
        );

        orderEntity.setTotalPrice(
                orderEntity.getOrderMenuItems().stream()
                        .map(val -> val.getMenuItem().getPrice())
                        .reduce(0.0, Double::sum)
        );

        Order savedOrder = jpaRepository.save(orderEntity);

        order.getOrderMenuItems().forEach(orderMenuItem -> {

            orderMenuItem.setOrderMenuItemId(new OrderMenuItemId(savedOrder.getId(), orderMenuItem.getMenuItem().getId()));
            orderMenuItem.setOrder(savedOrder);
            orderMenuItemRepository.save(orderMenuItem);

        });

        return baseMapper.entityToResponseDto(jpaRepository.getOne(savedOrder.getId()));

    }

    @Override
    public OrderResponseDto cancel(Long id) {
        return baseMapper.entityToResponseDto(
                    jpaRepository.findById(id)
                        .map(value -> {
                            if(value.getStatus().equals(Status.CANCELLED)) {
                                throw new OrderException("Order with id: " + id + " is already canceled!");
                            }
                            value.setStatus(Status.CANCELLED);
                            return jpaRepository.save(value);
                        })
                        .orElseThrow(() -> {
                            throw new OrderException("Order with id: " + id + " does not exist!");
                        })
                );
    }

    @Override
    public OrderResponseDto updateStatus(Long id, UpdateStatusDto status) {
        return baseMapper.entityToResponseDto(
                jpaRepository.findById(id)
                    .map(value -> {
                        if(value.getStatus().equals(Status.DELIVERED)) {
                            throw new OrderException("Order with id: " + id + " has status DELIVERED and cannot be changed!");
                        }
                        value.setStatus(status.getStatus());
                        if(value.getStatus().equals(Status.DELIVERED)) value.setDeliveryTime(LocalDateTime.now());
                        return jpaRepository.save(value);
                    })
                    .orElseThrow(() -> {
                        throw new OrderException("Order with id: " + id + " does not exist!");
                    })
        );

    }

    @Override
    public OrderResponseDto addMenuItemToOrder(Long orderId, OrderMenuItem order) {
        Order orderWithId = jpaRepository.findById(orderId)
                .orElseThrow(() -> new OrderException("Order with id: " + orderId + " does not exist!"));

        if(notValidStatusForUpdate(orderWithId.getStatus())) {
            throw new OrderException("You cannot add another menu item to order with id: " + orderId + " because it status is " + orderWithId.getStatus());
        }

        orderMenuItemRepository.findById(new OrderMenuItemId(order.getMenuItem().getId(), orderId))
                .ifPresentOrElse(value -> {
                    value.setAmount(value.getAmount() + order.getAmount());
                    value.setNote(value.getNote());
                    orderMenuItemRepository.save(value);
                }, () -> orderMenuItemRepository.save(generateOrderMenuItemEntity(orderId, order, orderWithId)));

        return baseMapper.entityToResponseDto(jpaRepository.getOne(orderId));
    }

    @Override
    public void deleteMenuItemFromOrder(Long orderId, Long menuItemId) {
        orderMenuItemRepository.deleteById(new OrderMenuItemId(orderId, menuItemId));
    }

    @Override
    public List<OrderResponseDto> findAllByFilter(OrderFilter orderFilter) {

        return orderRepository.findAllByFilter(orderFilter)
                .stream()
                .map(baseMapper::entityToResponseDto)
                .collect(Collectors.toList());

    }

    private OrderMenuItem generateOrderMenuItemEntity(Long orderId, OrderMenuItem order, Order orderWithId) {
        OrderMenuItem orderMenuItem = new OrderMenuItem();
        orderMenuItem.setOrderMenuItemId(new OrderMenuItemId(orderId, order.getMenuItem().getId()));
        orderMenuItem.setAmount(orderMenuItem.getAmount());
        orderMenuItem.setNote(orderMenuItem.getNote());
        orderMenuItem.setMenuItem(order.getMenuItem());
        orderMenuItem.setOrder(orderWithId);
        return orderMenuItem;
    }

    private boolean notValidStatusForUpdate(Status status) {
        return !status.equals(Status.ONGOING);
    }


}
