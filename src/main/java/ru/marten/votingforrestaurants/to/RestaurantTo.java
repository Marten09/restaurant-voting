package ru.marten.votingforrestaurants.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends BaseTo {
    String name;

    public RestaurantTo(Integer id, String name) {
        super(id);
        this.name = name;
    }
}
