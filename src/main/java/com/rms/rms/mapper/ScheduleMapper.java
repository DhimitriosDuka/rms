package com.rms.rms.mapper;


import com.rms.rms.dto.schedule.ScheduleCreateDto;
import com.rms.rms.dto.schedule.ScheduleResponseDto;
import com.rms.rms.dto.schedule.ScheduleUpdateDto;
import com.rms.rms.entity.Schedule;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper  extends BaseMapper<ScheduleCreateDto, ScheduleUpdateDto, ScheduleResponseDto, Schedule>{
    public ScheduleMapper() {
        super(ScheduleResponseDto.class, Schedule.class);
    }

    public Schedule getUpdatedSchedule(Schedule schedule, ScheduleUpdateDto scheduleUpdateDto) {
        schedule.setStartWorkHour(scheduleUpdateDto.getStartWorkHour());
        schedule.setEndWorkHour(scheduleUpdateDto.getEndWorkHour());
        schedule.setIsPresent(scheduleUpdateDto.getIsPresent());
        return schedule;
    }

}
