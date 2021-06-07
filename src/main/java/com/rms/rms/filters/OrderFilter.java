package com.rms.rms.filters;

import com.rms.rms.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderFilter {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Status status;
    private Double totalPrice;

}
