package com.rms.rms.dto.schedule;

import com.rms.rms.dto.user.UserResponseDto;
import com.rms.rms.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponseDto {

    private Long id;
    private LocalDate workDate;
    private LocalDateTime startWorkHour;
    private LocalDateTime endWorkHour;

}
