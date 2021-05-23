package com.rms.rms.dto.user;

import com.rms.rms.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class UserRoleUpdateDto {

    @NotNull(message = "Role must not be blank.")
    private Role role;

}
