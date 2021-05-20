package com.rms.rms.entity.embedded;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuItemIngredientId implements Serializable {

    private Long ingredientId;
    private Long menuItemId;

}
