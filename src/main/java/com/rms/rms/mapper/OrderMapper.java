package com.rms.rms.mapper;

import com.rms.rms.dto.order.OrderCreateDto;
import com.rms.rms.dto.order.OrderResponseDto;
import com.rms.rms.dto.order.UpdateStatusDto;
import com.rms.rms.entity.Order;
import org.springframework.stereotype.Component;


@Component
public class OrderMapper extends BaseMapper<OrderCreateDto, UpdateStatusDto, OrderResponseDto, Order> {

    public OrderMapper() {
        super(OrderResponseDto.class, Order.class);
    }

}
