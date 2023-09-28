package ru.marten.votingforrestaurants.testData;

import ru.marten.votingforrestaurants.MatcherFactory;
import ru.marten.votingforrestaurants.model.MenuItem;

import java.time.LocalDate;

import static ru.marten.votingforrestaurants.testData.RestaurantTestData.restaurant1;
import static ru.marten.votingforrestaurants.testData.RestaurantTestData.restaurant2;

public class MenuItemTestData {
    public static final MatcherFactory.Matcher<MenuItem> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(MenuItem.class, "restaurant", "menuDate");

    public static final int NOT_FOUND = 100;
    public static final int DISH1_ID = 1;

    public static final MenuItem MENU_ITEM_1 = new MenuItem(DISH1_ID, "Пюре", 500, LocalDate.now().plusDays(1));
    public static final MenuItem MENU_ITEM_2 = new MenuItem(DISH1_ID + 1, "Шашлык", 1000, LocalDate.now().plusDays(1));
    public static final MenuItem MENU_ITEM_3 = new MenuItem(DISH1_ID + 2, "Блины", 400, LocalDate.now().plusDays(1));
    public static final MenuItem MENU_ITEM_4 = new MenuItem(DISH1_ID + 3, "Пельмени", 250, LocalDate.now().plusDays(1));
    public static final MenuItem MENU_ITEM_5 = new MenuItem(DISH1_ID + 4, "Лосось в кляре", 900, LocalDate.now().plusDays(1));
    public static final MenuItem MENU_ITEM_6 = new MenuItem(DISH1_ID + 5, "Запеканка", 150, LocalDate.now());
    public static final MenuItem MENU_ITEM_7 = new MenuItem(DISH1_ID + 6, "Форель", 810, LocalDate.now());
    public static final MenuItem MENU_ITEM_8 = new MenuItem(DISH1_ID + 7, "Овощи на гриле", 410, LocalDate.now());
    public static final MenuItem MENU_ITEM_9 = new MenuItem(DISH1_ID + 8, "Шаурма", 280, LocalDate.now());
    public static final MenuItem MENU_ITEM_10 = new MenuItem(DISH1_ID + 9, "Пюре", 150, LocalDate.now());
    public static final MenuItem MENU_ITEM_11 = new MenuItem(DISH1_ID + 10, "Шашлык", 1000, LocalDate.now());
    public static final MenuItem MENU_ITEM_12 = new MenuItem(DISH1_ID + 11, "Блины", 230, LocalDate.now());
    public static final MenuItem MENU_ITEM_13 = new MenuItem(DISH1_ID + 12, "Пельмени", 250, LocalDate.now());
    public static final MenuItem MENU_ITEM_14 = new MenuItem(DISH1_ID + 13, "Лосось в кляре", 900, LocalDate.now());
    public static final MenuItem MENU_ITEM_15 = new MenuItem(DISH1_ID + 14, "Запеканка", 150, LocalDate.now());
    public static final MenuItem MENU_ITEM_16 = new MenuItem(DISH1_ID + 15, "Форель", 810, LocalDate.now());
    public static final MenuItem MENU_ITEM_17 = new MenuItem(DISH1_ID + 16, "Овощи на гриле", 410, LocalDate.now());
    public static final MenuItem MENU_ITEM_18 = new MenuItem(DISH1_ID + 17, "Шаурма с овощами", 220, LocalDate.now());
    public static final MenuItem MENU_ITEM_19 = new MenuItem(DISH1_ID + 18, "Шашлык с овощами", 1000, LocalDate.now());
    public static final MenuItem MENU_ITEM_20 = new MenuItem(DISH1_ID + 19, "Блины с мясом", 400, LocalDate.now());
    public static final MenuItem MENU_ITEM_21 = new MenuItem(DISH1_ID + 20, "Хинкали", 250, LocalDate.now());

    public static MenuItem getNew() {
        MenuItem newMenuItem = new MenuItem(null, "Новая еда", 100, LocalDate.now());
        newMenuItem.setRestaurant(restaurant2);
        return newMenuItem;
    }

    public static MenuItem getUpdated() {
        MenuItem updated = new MenuItem(MENU_ITEM_1);
        updated.setDescription("Updated");
        updated.setPrice(900);
        updated.setMenuDate(LocalDate.now());
        updated.setRestaurant(restaurant1);
        return updated;
    }
}
