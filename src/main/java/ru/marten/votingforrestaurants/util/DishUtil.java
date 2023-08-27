package ru.marten.votingforrestaurants.util;

import ru.marten.votingforrestaurants.model.Dish;
import ru.marten.votingforrestaurants.to.DishTo;

public class DishUtil {
    public static DishTo createTo(Dish dish) {
        return new DishTo(dish.getId(), dish.getDescription(), dish.getPrice(), dish.getRestaurant().getId());
    }
}
