package com.rms.rms.dto.order;

import com.rms.rms.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class UpdateStatusDto {

    private Status status;

}
