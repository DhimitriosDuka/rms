package com.rms.rms.service;

import com.rms.rms.dto.order.*;
import com.rms.rms.entity.Order;
import com.rms.rms.entity.OrderMenuItem;
import com.rms.rms.filters.OrderFilter;

import java.util.List;


public interface OrderService extends BaseService<OrderCreateDto, UpdateStatusDto, OrderResponseDto, Order>{

    OrderResponseDto cancel(Long id);
    OrderResponseDto updateStatus(Long id, UpdateStatusDto order);
    OrderResponseDto addMenuItemToOrder(Long orderId, OrderMenuItem order);
    void deleteMenuItemFromOrder(Long orderId, Long menuItemId);
    List<OrderResponseDto> findAllByFilter(OrderFilter orderFilter);
    List<OrderReportDto> getReportOfOrders();
    List<FoodGroupReportDto> getReportOfFoodCategory();
    List<OrderResponseDto> findAllOrdersOfUser();

}
