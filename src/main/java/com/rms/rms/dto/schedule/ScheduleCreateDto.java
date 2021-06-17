package com.rms.rms.dto.schedule;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleCreateDto {

    @NotNull(message = "Work date must not be null!")
    private LocalDate workDate;

    @NotNull(message = "Start work hour must not be null!")
    private LocalDateTime startWorkHour;

    @NotNull(message = "End work hour must not be null!")
    private LocalDateTime endWorkHour;

}
