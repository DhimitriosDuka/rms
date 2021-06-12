package com.rms.rms.utils;

import com.rms.rms.dto.order.OrderCreateDto;
import com.rms.rms.dto.order.OrderResponseDto;
import com.rms.rms.entity.MenuItem;
import com.rms.rms.entity.Order;
import com.rms.rms.entity.OrderMenuItem;

import com.rms.rms.entity.embedded.OrderMenuItemId;
import com.rms.rms.enums.Status;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

public class OrderUtil {

    private MenuItemUtil menuItemUtil;
    private UserUtil userUtil;

    public OrderUtil() {
        menuItemUtil = new MenuItemUtil();
        userUtil = new UserUtil();
    }

    public Order intiEntity(Long id) {

        Random random = new Random();
        Order order = new Order();
        order.setId(id);
        order.setDeliveryTime(LocalDateTime.now().plusMinutes(45));
        order.setTotalPrice(random.nextDouble());
        order.setTotalCalories(random.nextDouble());
        order.setAddress("Address");
        order.setCreatedAt(LocalDateTime.now());
        order.setPhoneNumber("0675401811");
        order.setStatus(Status.ONGOING);
        order.setOrderMenuItems(Arrays.asList(
                initOrderMenuItem(order, 1L),
                initOrderMenuItem(order, 2L)
        ));
        return order;

    }

    public OrderMenuItem initOrderMenuItem(Order order, Long menuItemId) {
        MenuItem menuItem = menuItemUtil.initEntity(menuItemId);
        OrderMenuItem orderMenuItem = new OrderMenuItem();
        orderMenuItem.setOrderMenuItemId(new OrderMenuItemId(order.getId(), menuItem.getId()));
        orderMenuItem.setOrder(order);
        orderMenuItem.setMenuItem(menuItem);
        orderMenuItem.setNote("Note");
        orderMenuItem.setAmount(new Random().nextDouble());
        return orderMenuItem;
    }

    public OrderCreateDto convertToCreateDto(Order entity) {
        OrderCreateDto orderCreateDto = new OrderCreateDto();
        orderCreateDto.setAddress(entity.getAddress());
        orderCreateDto.setPhoneNumber(entity.getPhoneNumber());
        orderCreateDto.setOrderMenuItems(entity.getOrderMenuItems());
        return orderCreateDto;
    }

    public OrderResponseDto convertToOrderResponse(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setCalories(order.getTotalCalories());
        orderResponseDto.setAddress(order.getAddress());
        orderResponseDto.setDeliveryTime(LocalDateTime.now());
        orderResponseDto.setStatus(Status.ONGOING);
        orderResponseDto.setPhoneNumber("");
        orderResponseDto.setPrice(order.getTotalPrice());
        orderResponseDto.setDeliveryGuy(userUtil.convertToResponseDto(order.getDeliveryGuy()));
        return orderResponseDto;
    }

}
