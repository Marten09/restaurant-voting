package ru.marten.votingforrestaurants.testData;

import ru.marten.votingforrestaurants.MatcherFactory;
import ru.marten.votingforrestaurants.model.Dish;

import java.time.LocalDate;
import java.time.Month;

import static java.time.LocalDate.now;
import static ru.marten.votingforrestaurants.model.AbstractBaseEntity.START_SEQ;
import static ru.marten.votingforrestaurants.testData.RestaurantTestData.*;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingEqualsComparator(Dish.class);

    public static final int NOT_FOUND = 10;
    public static final int DISH1_ID = START_SEQ + 6;
    public static final int DISH5_ID = DISH1_ID + 4;

    public static final Dish dish1 = new Dish(DISH1_ID, "Пюре", 500, LocalDate.of(2020, Month.JANUARY, 23), restaurant1);
    public static final Dish dish2 = new Dish(DISH1_ID + 1, "Шашлык", 1000, LocalDate.of(2020, Month.JANUARY, 24), restaurant1);
    public static final Dish dish3 = new Dish(DISH1_ID + 2, "Блины", 400, LocalDate.of(2020, Month.JANUARY, 25), restaurant1);
    public static final Dish dish4 = new Dish(DISH1_ID + 3, "Пельмени", 250, LocalDate.of(2020, Month.JANUARY, 26), restaurant1);
    public static final Dish dish5 = new Dish(DISH1_ID + 4, "Лосось в кляре", 900, LocalDate.of(2020, Month.JANUARY, 20), restaurant2);
    public static final Dish dish6 = new Dish(DISH1_ID + 5, "Запеканка", 150, LocalDate.of(2020, Month.JANUARY, 27), restaurant2);
    public static final Dish dish7 = new Dish(DISH1_ID + 6, "Форель", 810, LocalDate.of(2020, Month.JANUARY, 28), restaurant2);
    public static final Dish dish8 = new Dish(DISH1_ID + 7, "Овощи на гриле", 410, LocalDate.of(2020, Month.JANUARY, 29), restaurant3);
    public static final Dish dish9 = new Dish(DISH1_ID + 8, "Шаурма", 280, LocalDate.of(2020, Month.JANUARY, 30), restaurant3);

    public static Dish getNew() {
        return new Dish(null, "Новая еда", 100, now(), restaurant1);
    }

    public static Dish getUpdated() {
        Dish updated = new Dish(dish1);
        updated.setDescription("Updated");
        updated.setPrice(900);
        return updated;
    }
}
