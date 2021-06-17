package com.rms.rms.dto.schedule;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleUpdateDto {

    @NotNull(message = "Start work hour must not be null!")
    private LocalDateTime startWorkHour;

    @NotNull(message = "End work hour must not be null!")
    private LocalDateTime endWorkHour;

    @NotNull(message = "Present field must not be null!")
    private Boolean isPresent;

}
