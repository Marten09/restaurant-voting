package ru.marten.votingforrestaurants.testData;

import ru.marten.votingforrestaurants.MatcherFactory;
import ru.marten.votingforrestaurants.model.Restaurant;

import java.util.List;

import static ru.marten.votingforrestaurants.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingEqualsComparator(Restaurant.class);

    public static final int RESTAURANT1_ID = START_SEQ + 3;
    public static final int RESTAURANT2_ID = RESTAURANT1_ID + 1;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID, "Лисичка", List.of());
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT1_ID + 1, "Тетерев", List.of());
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT1_ID + 2, "Онегин Дача", List.of());

    public static Restaurant getNew() {
        return new Restaurant(null, "New restaurant", List.of());
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(restaurant1);
        updated.setName("Updated");
        updated.setMenuList(List.of());
        return updated;
    }
}
