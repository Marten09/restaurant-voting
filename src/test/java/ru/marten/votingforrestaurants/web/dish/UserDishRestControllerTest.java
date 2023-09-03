package ru.marten.votingforrestaurants.web.dish;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.marten.votingforrestaurants.testData.DishTestData;
import ru.marten.votingforrestaurants.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.marten.votingforrestaurants.testData.DishTestData.*;
import static ru.marten.votingforrestaurants.testData.RestaurantTestData.RESTAURANT1_ID;
import static ru.marten.votingforrestaurants.testData.UserTestData.USER_MAIL;

class UserDishRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = UserDishRestController.REST_URL + '/';

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + RESTAURANT1_ID + "/dishes/" + DISH1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dish1));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAllByRestaurant() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(DishTestData.getAllByRestaurant1()));
    }
}