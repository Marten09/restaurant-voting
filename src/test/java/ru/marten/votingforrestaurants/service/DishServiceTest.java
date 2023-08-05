package ru.marten.votingforrestaurants.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.marten.votingforrestaurants.model.Dish;
import ru.marten.votingforrestaurants.util.exception.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.marten.votingforrestaurants.testData.DishTestData.*;
import static ru.marten.votingforrestaurants.testData.RestaurantTestData.restaurant1;

public class DishServiceTest extends AbstractServiceTest {
    @Autowired
    private DishService service;

    @Test
    void create() {
        Dish created = service.create(getNew());
        int newId = created.id();
        Dish newUser = getNew();
        newUser.setId(newId);
        DISH_MATCHER.assertMatch(created, newUser);
        DISH_MATCHER.assertMatch(service.get(newId), newUser);
    }

    @Test
    void duplicateDateTimeCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Dish(null, "duplicate", 100, dish1.getRegistered(), restaurant1)));
    }

    @Test
    void update() {
        Dish updated = getUpdated();
        service.update(updated);
        DISH_MATCHER.assertMatch(service.get(DISH1_ID), getUpdated());
    }

    @Test
    void delete() {
        service.delete(DISH1_ID);
        assertThrows(NotFoundException.class, () -> service.get(DISH1_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    void get() {
        Dish actual = service.get(DISH1_ID);
        DISH_MATCHER.assertMatch(actual, dish1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    void getAll() {
        List<Dish> all = service.getAll();
        DISH_MATCHER.assertMatch(all, dish1, dish2, dish3, dish4, dish5, dish6, dish7, dish8, dish9);
    }
}