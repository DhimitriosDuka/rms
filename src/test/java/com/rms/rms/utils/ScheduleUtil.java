package com.rms.rms.utils;

import com.rms.rms.dto.schedule.ScheduleCreateDto;
import com.rms.rms.dto.schedule.ScheduleResponseDto;
import com.rms.rms.dto.schedule.ScheduleUpdateDto;
import com.rms.rms.entity.Schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ScheduleUtil {

    public Schedule initEntity(Long id) {
        Schedule schedule = new Schedule();
        schedule.setId(1L);
        schedule.setIsPresent(Boolean.TRUE);
        schedule.setStartWorkHour(LocalDateTime.now());
        schedule.setEndWorkHour(LocalDateTime.now().plusHours(8));
        return schedule;
    }

    public ScheduleUpdateDto convertToUpdateDto(Schedule schedule){
        ScheduleUpdateDto scheduleUpdateDto = new ScheduleUpdateDto();
        scheduleUpdateDto.setIsPresent(schedule.getIsPresent());
        scheduleUpdateDto.setStartWorkHour(schedule.getStartWorkHour().plusHours(2));
        scheduleUpdateDto.setEndWorkHour(schedule.getEndWorkHour());
        return scheduleUpdateDto;
    }

    public ScheduleCreateDto initCreateDto() {

        ScheduleCreateDto scheduleCreateDto = new ScheduleCreateDto();
        scheduleCreateDto.setStartWorkHour(LocalDateTime.now());
        scheduleCreateDto.setEndWorkHour(LocalDateTime.now().plusHours(8));
        scheduleCreateDto.setWorkDate(LocalDate.now());
        return scheduleCreateDto;

    }

    public ScheduleResponseDto initResponseDto(Schedule schedule) {
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto();
        scheduleResponseDto.setId(schedule.getId());
        scheduleResponseDto.setStartWorkHour(schedule.getStartWorkHour());
        scheduleResponseDto.setWorkDate(schedule.getWorkDate());
        scheduleResponseDto.setEndWorkHour(schedule.getEndWorkHour());
        return scheduleResponseDto;
    }


}
