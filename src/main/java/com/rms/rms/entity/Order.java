package com.rms.rms.entity;

import com.rms.rms.annotations.PhoneNumber;
import com.rms.rms.enums.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Status status = Status.ONGOING;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime deliveryTime = LocalDateTime.now().plusMinutes(45);

    @PhoneNumber
    private String phoneNumber;

    @NotBlank(message = "Address must not be blank!")
    private String address;

    @NotNull(message = "Total calories must not be null")
    private Double totalCalories;

    @NotNull(message = "Total price must not be null")
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "costumer_id", referencedColumnName = "id")
    private User costumer;

    @ManyToOne
    @JoinColumn(name = "delivery_guy_id", referencedColumnName = "id")
    private User deliveryGuy;

    @OneToMany(mappedBy = "order")
    private List<OrderMenuItem> orderMenuItems;

}
