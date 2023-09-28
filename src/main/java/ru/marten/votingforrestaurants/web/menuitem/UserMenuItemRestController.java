package ru.marten.votingforrestaurants.web.menuitem;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.marten.votingforrestaurants.model.MenuItem;

@RestController
@RequestMapping(value = UserMenuItemRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserMenuItemRestController extends AbstractMenuItemController {
    static final String REST_URL = "/api/user/restaurants";

    @GetMapping("/{restId}/dishes/{id}")
    public MenuItem get(@PathVariable int restId, @PathVariable int id) {
        return super.get(restId, id);
    }
}
