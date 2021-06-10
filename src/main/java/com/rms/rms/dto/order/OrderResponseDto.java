package com.rms.rms.dto.order;


import com.rms.rms.dto.user.UserResponseDto;
import com.rms.rms.entity.OrderMenuItem;
import com.rms.rms.entity.User;
import com.rms.rms.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {

    private Long id;
    private Status status;
    private LocalDateTime deliveryTime;
    private String phoneNumber;
    private String address;
    private Double calories;
    private Double price;
    private List<OrderMenuItemResponseDto> orderMenuItems;
    private UserResponseDto deliveryGuy;

}
