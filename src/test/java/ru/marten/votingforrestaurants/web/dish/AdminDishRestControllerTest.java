package ru.marten.votingforrestaurants.web.dish;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.marten.votingforrestaurants.error.NotFoundException;
import ru.marten.votingforrestaurants.model.Dish;
import ru.marten.votingforrestaurants.service.DishService;
import ru.marten.votingforrestaurants.testData.DishTestData;
import ru.marten.votingforrestaurants.to.DishTo;
import ru.marten.votingforrestaurants.util.DishUtil;
import ru.marten.votingforrestaurants.util.JsonUtil;
import ru.marten.votingforrestaurants.web.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.marten.votingforrestaurants.testData.DishTestData.*;
import static ru.marten.votingforrestaurants.testData.RestaurantTestData.RESTAURANT1_ID;
import static ru.marten.votingforrestaurants.testData.UserTestData.ADMIN_MAIL;

class AdminDishRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = AdminDishRestController.REST_URL + '/';
    @Autowired
    private DishService dishService;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithLocation() throws Exception {
        DishTo newDishTo = new DishTo(null, "новая еда", 233);
        Dish newDish = DishUtil.createNewFromTo(newDishTo);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL_SLASH + RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDishTo)))
                .andExpect(status().isCreated());

        Dish created = DISH_MATCHER.readFromJson(action);
        int newId = created.id();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(dishService.get(RESTAURANT1_ID, newId), newDish);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        Dish oldDish = dishService.get(RESTAURANT1_ID, DISH1_ID);
        DishTo updated = new DishTo(null, "updateDish", 300);
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + RESTAURANT1_ID + "/dishes/" + DISH1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        DISH_MATCHER.assertMatch(oldDish, DishUtil.updateFromTo(new Dish(dish1), updated));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + RESTAURANT1_ID + "/dishes/" + DISH1_ID))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> dishService.get(RESTAURANT1_ID, DISH1_ID));
    }


    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + RESTAURANT1_ID + "/dishes/" + DISH1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dish1));
    }

    @Test
    void getNotAuthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + DISH1_ID))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAllByRestaurant() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(DishTestData.getAllByRestaurant1()));
    }
}