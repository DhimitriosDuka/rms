package com.rms.rms.dto.order;

import com.rms.rms.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateStatusDto {

    @NotNull(message = "Status must not be null!")
    private Status status;

}
