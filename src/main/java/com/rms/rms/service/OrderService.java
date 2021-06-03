package com.rms.rms.service;

import com.rms.rms.dto.order.OrderCreateDto;
import com.rms.rms.dto.order.OrderResponseDto;
import com.rms.rms.dto.order.UpdateStatusDto;
import com.rms.rms.entity.Order;
import com.rms.rms.entity.OrderMenuItem;


public interface OrderService extends BaseService<OrderCreateDto, UpdateStatusDto, OrderResponseDto, Order>{

    OrderResponseDto cancel(Long id);
    OrderResponseDto updateStatus(Long id, UpdateStatusDto order);
    OrderResponseDto addMenuItemToOrder(Long orderId, OrderMenuItem order);
    void deleteMenuItemFromOrder(Long orderId, Long menuItemId);

}
