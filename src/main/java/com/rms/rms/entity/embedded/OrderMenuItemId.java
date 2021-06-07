package com.rms.rms.entity.embedded;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rms.rms.entity.MenuItem;
import com.rms.rms.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderMenuItemId implements Serializable {

    private Long orderId;
    private Long menuItemId;

}
