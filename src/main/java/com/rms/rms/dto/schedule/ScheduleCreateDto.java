package com.rms.rms.dto.schedule;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleCreateDto {

    private LocalDate workDate;

    private LocalDateTime startWorkHour;

    private LocalDateTime endWorkHour;

}
