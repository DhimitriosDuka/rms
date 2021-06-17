package com.rms.rms.dto.order;

import com.rms.rms.entity.OrderMenuItem;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
public class OrderCreateDto {

    @Length(min = 10, max = 13)
    @Pattern(regexp = "(0|(\\+|00)355)6([789])[0-9]{7,}", message = "Please enter a valid phone number!")
    private String phoneNumber;

    @NotBlank(message = "Address must not be blank!")
    private String address;

    @NotNull(message = "Menu items must not be null!")
    private List<OrderMenuItem> orderMenuItems;

}
