package ru.marten.votingforrestaurants.util;

import lombok.experimental.UtilityClass;
import ru.marten.votingforrestaurants.model.Dish;
import ru.marten.votingforrestaurants.to.DishTo;

import java.time.LocalDate;

@UtilityClass
public class DishUtil {
    public static DishTo createTo(Dish dish) {
        return new DishTo(dish.getId(), dish.getDescription(), dish.getPrice());
    }

    public static Dish createNewFromTo(DishTo dishTo) {
        return new Dish(null, dishTo.getDescription(), dishTo.getPrice(), LocalDate.now());
    }

    public static Dish updateFromTo(Dish dish, DishTo dishTo) {
        dish.setDescription(dishTo.getDescription());
        dish.setPrice(dishTo.getPrice());
        return dish;
    }
}
