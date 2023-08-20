package ru.marten.votingforrestaurants.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.marten.votingforrestaurants.error.NotFoundException;
import ru.marten.votingforrestaurants.model.Dish;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.marten.votingforrestaurants.testData.DishTestData.*;
import static ru.marten.votingforrestaurants.testData.RestaurantTestData.RESTAURANT1_ID;

public class DishServiceTest extends AbstractServiceTest {
    @Autowired
    private DishService service;

    @Test
    void create() {
        Dish created = service.create(getNew(), RESTAURANT1_ID);
        int newId = created.id();
        Dish newDish = getNew();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(service.get(newId, RESTAURANT1_ID), newDish);
    }

    @Test
    void update() {
        Dish updated = getUpdated();
        service.update(updated, RESTAURANT1_ID);
        DISH_MATCHER.assertMatch(service.get(DISH1_ID, RESTAURANT1_ID), getUpdated());
    }

    @Test
    void delete() {
        service.delete(DISH1_ID, RESTAURANT1_ID);
        assertThrows(NotFoundException.class, () -> service.get(DISH1_ID, RESTAURANT1_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, RESTAURANT1_ID));
    }

    @Test
    void get() {
        Dish actual = service.get(DISH1_ID, RESTAURANT1_ID);
        DISH_MATCHER.assertMatch(actual, dish1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, RESTAURANT1_ID));
    }

    @Test
    void getAll() {
        List<Dish> all = service.getAll(RESTAURANT1_ID);
        DISH_MATCHER.assertMatch(all, dish1, dish2, dish3, dish4, dish5, dish6, dish7, dish8, dish9);
    }
}