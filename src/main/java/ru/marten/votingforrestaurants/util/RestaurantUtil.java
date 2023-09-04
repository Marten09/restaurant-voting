package ru.marten.votingforrestaurants.util;

import lombok.experimental.UtilityClass;
import ru.marten.votingforrestaurants.model.Restaurant;
import ru.marten.votingforrestaurants.to.RestaurantTo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RestaurantUtil {
    public static RestaurantTo createTo(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName());
    }

    public static List<RestaurantTo> getTos(Collection<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantUtil::createTo)
                .collect(Collectors.toList());
    }
}
