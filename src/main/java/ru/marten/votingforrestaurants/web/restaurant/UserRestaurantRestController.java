package ru.marten.votingforrestaurants.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.marten.votingforrestaurants.model.Restaurant;
import ru.marten.votingforrestaurants.to.RestaurantTo;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantRestController extends AbstractRestaurantController {
    static final String REST_URL = "/api/user/restaurants";

    @GetMapping("/{id}/with-dishes")
    public Restaurant getWithDishes(@PathVariable int id) {
        return super.getWithDishes(id);
    }

    @GetMapping("/{id}/with-dishes/by-date")
    public Restaurant getWithDishesByDate(@PathVariable int id, @RequestParam LocalDate menuDate) {
        return super.getWithDishesByDate(id, menuDate);
    }

    @GetMapping
    public List<RestaurantTo> getAll() {
        return super.getAll();
    }
}
