package com.rms.rms.dto.user;

import com.rms.rms.enums.Role;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserRoleUpdateDto {

    @NotNull(message = "Role must not be blank.")
    private Role role;

}
