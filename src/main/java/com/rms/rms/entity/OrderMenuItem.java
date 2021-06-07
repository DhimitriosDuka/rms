package com.rms.rms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rms.rms.entity.embedded.OrderMenuItemId;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "orders_menu_items")
@Data
public class OrderMenuItem {

    @EmbeddedId
    private OrderMenuItemId orderMenuItemId;

    @NotNull(message = "Please provide an amount!")
    @Min(value = 0, message = "Amount should be greater than 0!")
    private Double amount;
    private String note;

    @ManyToOne
    @MapsId("orderId")
    @JsonIgnore
    private Order order;

    @ManyToOne
    @MapsId("menuItemId")
    private MenuItem menuItem;

}
