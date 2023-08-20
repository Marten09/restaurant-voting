package ru.marten.votingforrestaurants.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.marten.votingforrestaurants.model.Restaurant;

import static ru.marten.votingforrestaurants.testData.RestaurantTestData.*;

class RestaurantServiceTest extends AbstractServiceTest{
    @Autowired
    private RestaurantService service;

    @Test
    void create() {
        Restaurant created = service.create(getNew());
        int newId = created.id();
        Restaurant newRestaurant = getNew();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(service.get(newId), newRestaurant);
    }

    @Test
    void get() {
        Restaurant actual = service.get(RESTAURANT1_ID);
        RESTAURANT_MATCHER.assertMatch(actual, restaurant1);
    }

    @Test
    void getWithMenu() {
        Restaurant actual = service.getWithMenu(RESTAURANT1_ID);
        RESTAURANT_MATCHER.assertMatch(actual, restaurant1);
    }

    @Test
    void getWithMenuByDate() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getAllWithMenu() {
    }

    @Test
    void getAllWithMenuByDate() {
    }
}