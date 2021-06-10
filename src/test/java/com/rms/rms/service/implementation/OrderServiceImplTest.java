package com.rms.rms.service.implementation;

import com.rms.rms.dto.order.OrderCreateDto;
import com.rms.rms.dto.order.OrderResponseDto;
import com.rms.rms.dto.order.UpdateStatusDto;
import com.rms.rms.entity.Order;
import com.rms.rms.entity.User;
import com.rms.rms.entity.embedded.OrderMenuItemId;
import com.rms.rms.enums.Role;
import com.rms.rms.mapper.BaseMapper;
import com.rms.rms.repository.OrderMenuItemRepository;
import com.rms.rms.repository.OrderRepository;
import com.rms.rms.repository.UserRepository;
import com.rms.rms.utils.OrderUtil;
import com.rms.rms.utils.UserUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderMenuItemRepository orderMenuItemRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private JpaRepository<Order, Long> jpaRepository;

    @Mock
    private BaseMapper<OrderCreateDto, UpdateStatusDto, OrderResponseDto, Order> baseMapper;

    private OrderUtil orderUtil;
    private Order order;
    private OrderCreateDto orderCreateDto;
    private UserUtil userUtil;
    private User user;
    private User delivery;

    @BeforeEach
    void init() {
        orderService.jpaRepository = jpaRepository;
        orderService.baseMapper = baseMapper;
        orderUtil = new OrderUtil();
        userUtil = new UserUtil();
        order = orderUtil.intiEntity(1L);
        orderCreateDto = orderUtil.convertToCreateDto(order);
        user = userUtil.initEntity(1L, Role.OPERATOR);
        delivery = userUtil.initEntity(2L, Role.DELIVERY);
    }

//    @Test
//    void When_Save_ReturnCreatedOrder() {
//
//        when(userRepository.getOne(3L)).thenReturn(null);
//        when(baseMapper.createDtoToEntity(orderCreateDto)).thenReturn(order);
//        when(userRepository.getOne(user.getId())).thenReturn(user);
//        when(userRepository.findAllAvailableDeliveryGuys()).thenReturn(Collections.emptyList());
//        when(userRepository.findDeliveryGuyWithClosestDeliveryTime()).thenReturn(delivery);
//        when(jpaRepository.save(order)).thenReturn(order);
//
//        when(jpaRepository.getOne(order.getId())).thenReturn(order);
//        when(baseMapper.entityToResponseDto(order)).thenReturn(orderUtil.convertToOrderResponse(order));
//
//        Double expectedTotalCalories = order.getOrderMenuItems().stream()
//                .map(val -> val.getMenuItem().getCalories())
//                .reduce(0.0, Double::sum);
//
//        Double expectedTotalPrice = order.getOrderMenuItems().stream()
//                .map(val -> val.getMenuItem().getPrice())
//                .reduce(0.0, Double::sum);
//
//
//        OrderResponseDto orderResponseDto = orderService.save(orderCreateDto);
//
////        assertEquals(orderResponseDto.getCalories(), expectedTotalCalories);
////        assertEquals(orderResponseDto.getPrice(), expectedTotalPrice);
//        assertEquals(orderResponseDto.getDeliveryGuy().getUserName(), delivery.getUserName());
//
//
//    }

    @Test
    void testSave() {
    }

    @Test
    void cancel() {
    }

    @Test
    void updateStatus() {
    }

    @Test
    void addMenuItemToOrder() {
    }

    @Test
    void When_DeleteMenuItemFromOrder_Success() {

        OrderMenuItemId orderMenuItemId = new OrderMenuItemId(1L, 2L);



    }
}