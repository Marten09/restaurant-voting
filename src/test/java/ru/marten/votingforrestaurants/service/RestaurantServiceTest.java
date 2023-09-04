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
import static ru.marten.votingforrestaurants.testData.DishTestData.*;
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
        RESTAURANT_MATCHER.assertMatch(service.getWithMenu(newId), newRestaurant);
    }

    @Test
    void update() {
        Restaurant updated = RestaurantTestData.getUpdated();
        service.update(updated);
        RESTAURANT_MATCHER.assertMatch(service.getWithMenu(RESTAURANT1_ID), RestaurantTestData.getUpdated());
    }

    @Test
    void delete() {
        service.delete(RESTAURANT1_ID);
        assertThrows(NotFoundException.class, () -> service.getWithMenu(RESTAURANT1_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    void getWithMenu() {
        Restaurant actual = service.getWithMenu(RESTAURANT1_ID);
        restaurant1.setMenuList(List.of(dish1, dish2, dish3, dish4, dish5, dish6, dish7, dish8, dish9));
        RESTAURANT_WITH_MENU_MATCHER.assertMatch(actual, restaurant1);
    }

    @Test
    void getWithMenuByDate() {
        Restaurant actual = service.getWithMenuByDate(RESTAURANT1_ID, LocalDate.now());
        Restaurant expected = getRestaurantWithMenuByDate(restaurant1, LocalDate.now());
        RESTAURANT_WITH_MENU_MATCHER.assertMatch(actual, expected);
    }

    @Test
    void getAll() {
        List<RestaurantTo> all = service.getAll();
        RESTAURANT_TO_MATCHER.assertMatch(all, RestaurantUtil.getTos(RestaurantTestData.getAll()));
    }
}