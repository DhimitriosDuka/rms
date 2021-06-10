package com.rms.rms.service.implementation;

import com.rms.rms.dto.schedule.ScheduleCreateDto;
import com.rms.rms.dto.schedule.ScheduleResponseDto;
import com.rms.rms.dto.schedule.ScheduleUpdateDto;
import com.rms.rms.dto.user.UserCreateDto;
import com.rms.rms.dto.user.UserResponseDto;
import com.rms.rms.dto.user.UserUpdateDto;
import com.rms.rms.dto.user.UserUpdatePasswordDto;
import com.rms.rms.entity.Schedule;
import com.rms.rms.entity.User;
import com.rms.rms.enums.Role;
import com.rms.rms.exception.UserException;
import com.rms.rms.mapper.ScheduleMapper;
import com.rms.rms.repository.OrderRepository;
import com.rms.rms.repository.ScheduleRepository;
import com.rms.rms.utils.OrderUtil;
import com.rms.rms.utils.ScheduleUtil;
import com.rms.rms.utils.UserUtil;
import org.hibernate.JDBCException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest extends BaseServiceTest<UserCreateDto, UserUpdateDto, UserResponseDto, User>{

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private ScheduleMapper scheduleMapper;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserUtil userUtil;
    private OrderUtil orderUtil;
    private ScheduleUtil scheduleUtil;
    private User operator, delivery;
    private Schedule schedule;
    private ScheduleResponseDto scheduleResponseDto;
    private ScheduleUpdateDto scheduleUpdateDto;
    private ScheduleCreateDto scheduleCreateDto;

    @BeforeEach
    void init(){
        userUtil = new UserUtil();
        orderUtil = new OrderUtil();
        scheduleUtil = new ScheduleUtil();
        operator = userUtil.initEntity(1L, Role.OPERATOR);
        delivery = userUtil.initEntity(1L, Role.DELIVERY);
        schedule = scheduleUtil.initEntity(1L);
        scheduleResponseDto = scheduleUtil.initResponseDto(schedule);
        scheduleUpdateDto = scheduleUtil.convertToUpdateDto(schedule);
        scheduleCreateDto = scheduleUtil.initCreateDto();
    }

    @Test
    void When_Update_ReturnUpdatedUser() {

        UserUpdateDto userUpdateDto = userUtil.convertToUserUpdateDto(operator);
        User updatedUser = userUtil.initEntity(1L, Role.OPERATOR);
        updatedUser.setUserName(userUpdateDto.getUserName());

        UserResponseDto updatedUserResponseDto = userUtil.convertToResponseDto(updatedUser);

        when(jpaRepository.findById(operator.getId())).thenReturn(java.util.Optional.of(operator));
        when(jpaRepository.save(operator)).thenReturn(updatedUser);
        when(baseMapper.entityToResponseDto(updatedUser)).thenReturn(updatedUserResponseDto);
        assertEquals(userService.update(operator.getId(), userUpdateDto).getUserName(), userUpdateDto.getUserName());

    }

    @Test
    void When_Update_ThrowJDBCExceptionDuplicateEmail() {

        UserUpdateDto userUpdateDto = userUtil.convertToUserUpdateDto(operator);
        User updatedUser = userUtil.initEntity(1L, Role.OPERATOR);
        updatedUser.setUserName(userUpdateDto.getUserName());

        when(jpaRepository.findById(operator.getId())).thenReturn(java.util.Optional.of(operator));
        when(jpaRepository.save(operator)).thenThrow(JDBCException.class);

        assertThrows(JDBCException.class, () -> userService.update(operator.getId(), userUpdateDto));

    }

    @Test
    void When_Delete_SetOperatorActiveToFalse() {

        when(jpaRepository.findById(operator.getId())).thenReturn(Optional.of(operator));
        when(orderRepository.getOrdersOfOperator(operator)).thenReturn(Collections.emptyList());

        userService.delete(operator.getId());
        verify(jpaRepository).save(operator);

    }

    @Test
    void When_Delete_ThrowUserExceptionUserCannotBeDeleted() {

        when(jpaRepository.findById(operator.getId())).thenReturn(Optional.of(operator));
        when(orderRepository.getOrdersOfOperator(operator)).thenReturn(Collections.singletonList(orderUtil.intiEntity(1L)));

        assertThrows(UserException.class, () -> userService.delete(operator.getId()));

    }

    @Test
    void When_UpdatePassword_SaveUpdatedPassword() {

        UserUpdatePasswordDto userUpdatePasswordDto = new UserUpdatePasswordDto();
        userUpdatePasswordDto.setPassword("NewPassword");

        when(jpaRepository.findById(operator.getId())).thenReturn(Optional.of(operator));
        when(jpaRepository.save(operator)).thenReturn(operator);

        userService.updatePassword(operator.getId(), userUpdatePasswordDto);
        verify(jpaRepository).save(operator);

    }

    @Test
    void When_AddScheduleToDeliveryGuy_ReturnScheduleCreated() {

        when(jpaRepository.findById(delivery.getId())).thenReturn(Optional.of(delivery));
        when(scheduleMapper.createDtoToEntity(scheduleCreateDto)).thenReturn(schedule);
        when(scheduleRepository.save(schedule)).thenReturn(schedule);
        when(scheduleMapper.entityToResponseDto(schedule)).thenReturn(scheduleResponseDto);

        assertEquals(schedule.getStartWorkHour(), userService.addScheduleToDeliveryGuy(delivery.getId(), scheduleCreateDto).getStartWorkHour());

    }

    @Test
    void When_AddScheduleToDeliveryGuy_ThrowUserExceptionOfWrongRole() {

        when(jpaRepository.findById(operator.getId())).thenReturn(Optional.of(operator));
        assertThrows(UserException.class, () -> userService.addScheduleToDeliveryGuy(operator.getId(), scheduleCreateDto));

    }

    @Test
    void When_UpdateScheduleOfDeliveryGuy_ReturnUpdatedSchedule() {

        when(scheduleRepository.findById(schedule.getId())).thenReturn(Optional.of(schedule));
        when(scheduleMapper.getUpdatedSchedule(schedule, scheduleUpdateDto)).thenReturn(schedule);
        when(scheduleRepository.save(schedule)).thenReturn(schedule);
        when(scheduleMapper.entityToResponseDto(schedule)).thenReturn(new ScheduleResponseDto(
                schedule.getId(),
                schedule.getWorkDate(),
                scheduleUpdateDto.getStartWorkHour(),
                schedule.getEndWorkHour()
        ));

        assertEquals(scheduleUpdateDto.getStartWorkHour(), userService.updateScheduleOfDeliveryGuy(schedule.getId(), scheduleUpdateDto).getStartWorkHour());

    }
}