package ru.marten.votingforrestaurants.web.dish;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.marten.votingforrestaurants.model.Dish;

import java.util.List;

@RestController
@RequestMapping(value = UserDishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserDishRestController extends AbstractDishController {
    static final String REST_URL = "/api/user/restaurants";

    @GetMapping("/{restId}/dishes/{id}")
    public Dish get(@PathVariable int restId, @PathVariable int id) {
        return super.get(restId, id);
    }

    @Override
    @GetMapping("/{restId}")
    public List<Dish> getAllByRestaurant(@PathVariable int restId) {
        return super.getAllByRestaurant(restId);
    }
}
