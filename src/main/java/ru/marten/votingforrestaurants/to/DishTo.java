package ru.marten.votingforrestaurants.to;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class DishTo extends BaseTo {
    String description;

    int price;

    @NotNull
    Integer restaurantId;

    public DishTo(Integer id, String description, int price, Integer restaurantId) {
        super(id);
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
    }
}
