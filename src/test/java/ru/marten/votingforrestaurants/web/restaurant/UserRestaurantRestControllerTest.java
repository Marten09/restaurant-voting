package ru.marten.votingforrestaurants.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import ru.marten.votingforrestaurants.testData.RestaurantTestData;
import ru.marten.votingforrestaurants.web.AbstractControllerTest;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.marten.votingforrestaurants.testData.RestaurantTestData.RESTAURANT1_ID;
import static ru.marten.votingforrestaurants.testData.RestaurantTestData.RESTAURANT_MATCHER;
import static ru.marten.votingforrestaurants.testData.UserTestData.USER_MAIL;
import static ru.marten.votingforrestaurants.web.restaurant.UserRestaurantRestController.REST_URL;

class UserRestaurantRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = REST_URL + '/';

    @Test
    @WithUserDetails(value = USER_MAIL)
    @Transactional
    void getWithMenu() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + RESTAURANT1_ID + "/with-menu"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    @Transactional
    void getWithMenuByDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + RESTAURANT1_ID + "/by-date")
                .param("registeredDate", String.valueOf(LocalDate.now())))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RestaurantTestData.getAll()));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAllWithMenu() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "with-menu"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RestaurantTestData.getAll()));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAllWithMenuByDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "with-menu" + "/by-date")
                .param("registeredDate", String.valueOf(LocalDate.now())))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RestaurantTestData.getAll()));
    }
}