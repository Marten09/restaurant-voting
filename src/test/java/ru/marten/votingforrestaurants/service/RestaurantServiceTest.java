package ru.marten.votingforrestaurants.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.marten.votingforrestaurants.error.NotFoundException;
import ru.marten.votingforrestaurants.model.Restaurant;
import ru.marten.votingforrestaurants.testData.RestaurantTestData;
import ru.marten.votingforrestaurants.to.RestaurantTo;
import ru.marten.votingforrestaurants.util.RestaurantUtil;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.marten.votingforrestaurants.testData.MenuItemTestData.*;
import static ru.marten.votingforrestaurants.testData.RestaurantTestData.*;
import static ru.marten.votingforrestaurants.testData.RestaurantTestData.NOT_FOUND;

class RestaurantServiceTest extends AbstractServiceTest {
    @Autowired
    private RestaurantService service;

    @Test
    void create() {
        Restaurant created = service.create(RestaurantTestData.getNew());
        int newId = created.id();
        Restaurant newRestaurant = RestaurantTestData.getNew();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(service.getWithDishes(newId), newRestaurant);
    }

    @Test
    void update() {
        Restaurant updated = RestaurantTestData.getUpdated();
        service.update(updated);
        RESTAURANT_MATCHER.assertMatch(service.getWithDishes(RESTAURANT1_ID), RestaurantTestData.getUpdated());
    }

    @Test
    void delete() {
        service.delete(RESTAURANT1_ID);
        assertThrows(NotFoundException.class, () -> service.getWithDishes(RESTAURANT1_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    void getWithDishes() {
        Restaurant actual = service.getWithDishes(RESTAURANT1_ID);
        restaurant1.setMenuList(List.of(MENU_ITEM_1, MENU_ITEM_2, MENU_ITEM_3, MENU_ITEM_4, MENU_ITEM_5, MENU_ITEM_6, MENU_ITEM_7, MENU_ITEM_8, MENU_ITEM_9));
        RESTAURANT_WITH_MENU_MATCHER.assertMatch(actual, restaurant1);
    }

    @Test
    void getWithDishesByDate() {
        Restaurant actual = service.getWithDishesByDate(RESTAURANT1_ID, LocalDate.now());
        Restaurant expected = getRestaurantWithDishesByDate(restaurant1, LocalDate.now());
        RESTAURANT_WITH_MENU_MATCHER.assertMatch(actual, expected);
    }

    @Test
    void getAll() {
        List<RestaurantTo> all = service.getAll();
        RESTAURANT_TO_MATCHER.assertMatch(all, RestaurantUtil.getTos(RestaurantTestData.getAll()));
    }
}