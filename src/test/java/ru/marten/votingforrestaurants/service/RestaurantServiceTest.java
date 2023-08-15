package ru.marten.votingforrestaurants.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.marten.votingforrestaurants.model.Restaurant;
import ru.marten.votingforrestaurants.repository.RestaurantRepository;

import static org.junit.jupiter.api.Assertions.*;
import static ru.marten.votingforrestaurants.testData.RestaurantTestData.*;

class RestaurantServiceTest extends AbstractServiceTest {
    @Autowired
    private RestaurantRepository repository;

    @Test
    void get() {
    }

    @Test
    void getWithMenu() {
        Restaurant actual = repository.getWithMenu(RESTAURANT1_ID);
        RESTAURANT_WITH_MENU_MATCHER.assertMatch(actual, restaurant1);
    }
}