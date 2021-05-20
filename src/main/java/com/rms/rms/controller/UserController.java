package com.rms.rms.controller;

import com.rms.rms.dto.user.*;
import com.rms.rms.service.UserService;
import com.rms.rms.utils.Path;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(Path.USER_PATH)
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> save(@RequestBody UserCreateDto user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping(Path.ID)
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping(Path.ID)
    public void deleteById(@PathVariable Long id){
        userService.delete(id);
    }

    @PutMapping(Path.ID)
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id, @RequestBody UserUpdateDto user) {
        return new ResponseEntity<>(userService.update(id, user), HttpStatus.CREATED);
    }

    @PutMapping(Path.UPDATE_ROLE_OF_USER_WITH_ID)
    public ResponseEntity<UserResponseDto> updateRole(@PathVariable Long id, @RequestBody UserRoleUpdateDto role) {
        return new ResponseEntity<>(userService.updateRole(id, role), HttpStatus.CREATED);
    }

    @PutMapping(Path.UPDATE_PASSWORD_OF_USER_WITH_ID)
    public void updatePassword(@PathVariable Long id, @RequestBody UserUpdatePasswordDto password) {
        userService.updatePassword(id, password);
    }


}
