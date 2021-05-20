package com.rms.rms.dto.user;

import com.rms.rms.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserRoleUpdateDto {

    private Role role;

}
