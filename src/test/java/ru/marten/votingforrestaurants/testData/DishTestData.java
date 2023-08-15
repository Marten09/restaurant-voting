package ru.marten.votingforrestaurants.testData;

import ru.marten.votingforrestaurants.MatcherFactory;
import ru.marten.votingforrestaurants.model.Dish;

import java.time.LocalDate;
import java.util.List;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingEqualsComparator(Dish.class);

    public static final int NOT_FOUND = 100;
    public static final int DISH1_ID = 1;

    public static final Dish dish1 = new Dish(DISH1_ID, "Пюре", 500, LocalDate.now());
    public static final Dish dish2 = new Dish(DISH1_ID + 1, "Шашлык", 1000, LocalDate.now());
    public static final Dish dish3 = new Dish(DISH1_ID + 2, "Блины", 400, LocalDate.now());
    public static final Dish dish4 = new Dish(DISH1_ID + 3, "Пельмени", 250, LocalDate.now());
    public static final Dish dish5 = new Dish(DISH1_ID + 4, "Лосось в кляре", 900, LocalDate.now());
    public static final Dish dish6 = new Dish(DISH1_ID + 5, "Запеканка", 150, LocalDate.now());
    public static final Dish dish7 = new Dish(DISH1_ID + 6, "Форель", 810, LocalDate.now());
    public static final Dish dish8 = new Dish(DISH1_ID + 7, "Овощи на гриле", 410, LocalDate.now());
    public static final Dish dish9 = new Dish(DISH1_ID + 8, "Шаурма", 280, LocalDate.now());
    public static final Dish dish10 = new Dish(DISH1_ID + 9, "Пюре", 150, LocalDate.now());
    public static final Dish dish11= new Dish(DISH1_ID + 10, "Шашлык", 1000, LocalDate.now());
    public static final Dish dish12 = new Dish(DISH1_ID + 11, "Блины", 230, LocalDate.now());
    public static final Dish dish13 = new Dish(DISH1_ID + 12, "Пельмени", 250, LocalDate.now());
    public static final Dish dish14 = new Dish(DISH1_ID + 13, "Лосось в кляре", 900, LocalDate.now());
    public static final Dish dish15 = new Dish(DISH1_ID + 14, "Запеканка", 150, LocalDate.now());
    public static final Dish dish16 = new Dish(DISH1_ID + 15, "Форель", 810, LocalDate.now());
    public static final Dish dish17 = new Dish(DISH1_ID + 16, "Овощи на гриле", 410, LocalDate.now());
    public static final Dish dish18 = new Dish(DISH1_ID + 17, "Шаурма с овощами", 220, LocalDate.now());
    public static final Dish dish19= new Dish(DISH1_ID + 18, "Шашлык с овощами", 1000, LocalDate.now());
    public static final Dish dish20 = new Dish(DISH1_ID + 19, "Блины с мясом", 400, LocalDate.now());
    public static final Dish dish21 = new Dish(DISH1_ID + 20, "Хинкали", 250, LocalDate.now());

    public static Dish getNew() {
        return new Dish(null, "Новая еда", 100, LocalDate.now());
    }

    public static List<Dish> getAll() {
        return List.of(dish1, dish2, dish3, dish4, dish5, dish6, dish7, dish8, dish9, dish10, dish11, dish12, dish13,
                dish14, dish15, dish16, dish17, dish18, dish19, dish20, dish21);
    }

    public static List<Dish> getAllByRestaurant1() {
        return List.of(dish1, dish2, dish3, dish4, dish5, dish6, dish7, dish8, dish9, dish10);
    }

    public static Dish getUpdated() {
        Dish updated = new Dish(dish1);
        updated.setDescription("Updated");
        updated.setPrice(900);
        return updated;
    }
}
