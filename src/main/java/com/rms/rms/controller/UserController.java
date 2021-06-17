package com.rms.rms.controller;

import com.rms.rms.dto.schedule.ScheduleCreateDto;
import com.rms.rms.dto.schedule.ScheduleResponseDto;
import com.rms.rms.dto.schedule.ScheduleUpdateDto;
import com.rms.rms.dto.user.*;
import com.rms.rms.security.entity.AuthenticationRequest;
import com.rms.rms.security.entity.AuthenticationResponse;
import com.rms.rms.service.UserService;
import com.rms.rms.utils.Path;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(Path.USER_PATH)
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> save(@Valid @RequestBody UserCreateDto user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(Path.ID)
    public ResponseEntity<UserResponseDto> findById(@PathVariable @Min(0) Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_OPERATOR')")
    @DeleteMapping(Path.ID)
    public void deleteById(@PathVariable @Min(0) Long id){
        userService.delete(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_OPERATOR', 'ROLE_ADMIN', 'ROLE_DELIVERY')")
    @PutMapping(Path.ID)
    public ResponseEntity<UserResponseDto> update(@PathVariable @Min(0) Long id, @Valid @RequestBody UserUpdateDto user) {
        return new ResponseEntity<>(userService.update(id, user), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_OPERATOR', 'ROLE_ADMIN', 'ROLE_DELIVERY')")
    @PutMapping(Path.UPDATE_PASSWORD_OF_USER_WITH_ID)
    public void updatePassword(@PathVariable @Min(0) Long id, @Valid @RequestBody UserUpdatePasswordDto password) {
        userService.updatePassword(id, password);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(Path.SCHEDULE_PATH)
    public ResponseEntity<ScheduleResponseDto> addScheduleToDeliveryGuy(@PathVariable @Min(0) Long userId, @Valid @RequestBody ScheduleCreateDto schedule) {
        return new ResponseEntity<>(userService.addScheduleToDeliveryGuy(userId, schedule), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DELIVERY')")
    @PutMapping(Path.SCHEDULE_UPDATE_PATH)
    public ResponseEntity<ScheduleResponseDto> updateScheduleOfDeliveryGuy(@PathVariable @Min(0) Long scheduleId, @Valid @RequestBody ScheduleUpdateDto schedule) {
        return new ResponseEntity<>(userService.updateScheduleOfDeliveryGuy(scheduleId, schedule), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(Path.TOP_N_PATH)
    public ResponseEntity<List<UserResponseDto>> findTopCustomers(@PathVariable @Min(0) Integer n) {
        return new ResponseEntity<>(userService.findTopCustomers(n), HttpStatus.OK);
    }

    @PostMapping(Path.LOGIN)
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        return new ResponseEntity<>(userService.authenticate(authenticationRequest), HttpStatus.OK);
    }

}
