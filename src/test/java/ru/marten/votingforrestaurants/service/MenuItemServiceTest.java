package ru.marten.votingforrestaurants.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.marten.votingforrestaurants.error.NotFoundException;
import ru.marten.votingforrestaurants.model.MenuItem;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.marten.votingforrestaurants.testData.MenuItemTestData.*;
import static ru.marten.votingforrestaurants.testData.RestaurantTestData.RESTAURANT1_ID;

public class MenuItemServiceTest extends AbstractServiceTest {
    @Autowired
    private MenuItemService service;

    @Test
    void create() {
        MenuItem created = service.create(getNew(), RESTAURANT1_ID);
        int newId = created.id();
        MenuItem newMenuItem = getNew();
        newMenuItem.setId(newId);
        DISH_MATCHER.assertMatch(created, newMenuItem);
    }

    @Test
    void update() {
        MenuItem updated = getUpdated();
        service.update(updated, RESTAURANT1_ID);
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
        MenuItem actual = service.get(RESTAURANT1_ID, DISH1_ID);
        DISH_MATCHER.assertMatch(actual, MENU_ITEM_1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(RESTAURANT1_ID, NOT_FOUND));
    }
}