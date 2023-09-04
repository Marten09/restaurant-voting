package ru.marten.votingforrestaurants.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class DishTo extends BaseTo {
    String description;

    int price;


    public DishTo(Integer id, String description, int price) {
        super(id);
        this.description = description;
        this.price = price;
    }
}
