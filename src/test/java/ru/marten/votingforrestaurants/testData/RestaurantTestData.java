package ru.marten.votingforrestaurants.testData;

import ru.marten.votingforrestaurants.MatcherFactory;
import ru.marten.votingforrestaurants.model.Dish;
import ru.marten.votingforrestaurants.model.Restaurant;
import ru.marten.votingforrestaurants.to.RestaurantTo;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.marten.votingforrestaurants.testData.DishTestData.*;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menuList");
    public static final MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(RestaurantTo.class);
    public static MatcherFactory.Matcher<Restaurant> RESTAURANT_WITH_MENU_MATCHER =
            MatcherFactory.usingAssertions(Restaurant.class,
//     No need use ignoringAllOverriddenEquals, see https://assertj.github.io/doc/#breaking-changes
                    (a, e) -> assertThat(a).usingRecursiveComparison().ignoringFields("menuList.restaurant").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final int RESTAURANT1_ID = 1;
    public static final int NOT_FOUND = 1000;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID, "Лисичка");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT1_ID + 1, "Нияма");
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT1_ID + 2, "Онегин Дача");

    static {
        restaurant1.setMenuList(List.of(dish1, dish2, dish3, dish4, dish5, dish6, dish7, dish8, dish9));
        restaurant2.setMenuList(List.of(dish10, dish11, dish12, dish13, dish14, dish15));
        restaurant3.setMenuList(List.of(dish16, dish17, dish18, dish19, dish20, dish21));
    }

    public static List<Restaurant> getAll() {
        return List.of(restaurant1, restaurant2, restaurant3);
    }

    public static Restaurant getRestaurantWithMenuByDate(Restaurant restaurant, LocalDate localDate) {
        List<Dish> menuByDate = restaurant.getMenuList().stream()
                .filter(dish -> Objects.equals(dish.getRegistered(), localDate)).toList();
        restaurant.setMenuList(menuByDate);
        return restaurant;
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "New restaurant");
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(restaurant1);
        updated.setName("Updated");
        updated.setMenuList(List.of());
        return updated;
    }
}
