package com.rms.rms.dto.schedule;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleUpdateDto {

    private LocalDateTime startWorkHour;
    private LocalDateTime endWorkHour;
    private Boolean isPresent;

}
