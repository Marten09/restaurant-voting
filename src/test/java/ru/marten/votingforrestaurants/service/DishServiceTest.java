package ru.marten.votingforrestaurants.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.marten.votingforrestaurants.error.NotFoundException;
import ru.marten.votingforrestaurants.model.Dish;
import ru.marten.votingforrestaurants.to.DishTo;
import ru.marten.votingforrestaurants.util.DishUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.marten.votingforrestaurants.testData.DishTestData.*;
import static ru.marten.votingforrestaurants.testData.RestaurantTestData.RESTAURANT1_ID;

public class DishServiceTest extends AbstractServiceTest {
    @Autowired
    private DishService service;

    @Test
    void create() {
        DishTo created = service.create(getNew());
        int newId = created.id();
        DishTo newDish = DishUtil.createTo(getNew());
        newDish.setId(newId);
        DISH_TO_MATCHER.assertMatch(created, newDish);
    }

    @Test
    void update() {
        Dish updated = getUpdated();
        service.update(updated);
        DISH_MATCHER.assertMatch(service.get(RESTAURANT1_ID, DISH1_ID), getUpdated());
    }

    @Test
    void delete() {
        service.delete(RESTAURANT1_ID, DISH1_ID);
        assertThrows(NotFoundException.class, () -> service.get(RESTAURANT1_ID, DISH1_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(RESTAURANT1_ID, NOT_FOUND));
    }

    @Test
    void get() {
        Dish actual = service.get(RESTAURANT1_ID, DISH1_ID);
        DISH_MATCHER.assertMatch(actual, dish1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(RESTAURANT1_ID, NOT_FOUND));
    }

    @Test
    void getAllByRestaurant() {
        List<Dish> all = service.getAllByRestaurant(RESTAURANT1_ID);
        DISH_MATCHER.assertMatch(all, dish1, dish2, dish3, dish4, dish5, dish6, dish7, dish8, dish9);
    }
}