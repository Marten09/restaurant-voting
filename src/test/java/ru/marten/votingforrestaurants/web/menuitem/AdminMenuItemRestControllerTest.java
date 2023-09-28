package ru.marten.votingforrestaurants.web.menuitem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.marten.votingforrestaurants.error.NotFoundException;
import ru.marten.votingforrestaurants.model.MenuItem;
import ru.marten.votingforrestaurants.service.MenuItemService;
import ru.marten.votingforrestaurants.util.JsonUtil;
import ru.marten.votingforrestaurants.web.AbstractControllerTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.marten.votingforrestaurants.testData.MenuItemTestData.*;
import static ru.marten.votingforrestaurants.testData.RestaurantTestData.RESTAURANT1_ID;
import static ru.marten.votingforrestaurants.testData.UserTestData.ADMIN_MAIL;

class AdminMenuItemRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = AdminMenuItemRestController.REST_URL + '/';
    @Autowired
    private MenuItemService menuItemService;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithLocation() throws Exception {
        MenuItem newMenuItem = new MenuItem(null, "новая еда", 333, LocalDate.now());
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL_SLASH + RESTAURANT1_ID + "/dishes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMenuItem)))
                .andExpect(status().isCreated());

        MenuItem created = DISH_MATCHER.readFromJson(action);
        int newId = created.id();
        newMenuItem.setId(newId);
        DISH_MATCHER.assertMatch(created, newMenuItem);
        DISH_MATCHER.assertMatch(menuItemService.get(RESTAURANT1_ID, newId), newMenuItem);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        MenuItem oldMenuItem = menuItemService.get(RESTAURANT1_ID, DISH1_ID);
        MenuItem updated = new MenuItem(DISH1_ID, "updated", 333, LocalDate.now());
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + RESTAURANT1_ID + "/dishes/" + DISH1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        DISH_MATCHER.assertMatch(oldMenuItem, updated);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + RESTAURANT1_ID + "/dishes/" + DISH1_ID))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> menuItemService.get(RESTAURANT1_ID, DISH1_ID));
    }


    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + RESTAURANT1_ID + "/dishes/" + DISH1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(MENU_ITEM_1));
    }

    @Test
    void getNotAuthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + DISH1_ID))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}