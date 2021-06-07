package com.rms.rms.dto.order;

import com.rms.rms.entity.MenuItem;
import com.rms.rms.entity.MenuItemIngredient;
import com.rms.rms.entity.OrderMenuItem;
import com.rms.rms.enums.Status;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderCreateDto {

    private String phoneNumber;
    private String address;
    private List<OrderMenuItem> orderMenuItems;

}
