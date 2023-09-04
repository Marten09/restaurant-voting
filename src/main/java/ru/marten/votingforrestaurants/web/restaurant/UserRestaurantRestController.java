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

    @GetMapping("/{id}/with-menu")
    public Restaurant getWithMenu(@PathVariable int id) {
        return super.getWithMenu(id);
    }

    @GetMapping("/{id}/by-date")
    public Restaurant getWithMenuByDate(@PathVariable int id, @RequestParam LocalDate registeredDate) {
        return super.getWithMenuByDate(id, registeredDate);
    }

    @GetMapping
    public List<RestaurantTo> getAll() {
        return super.getAll();
    }

    @GetMapping("/with-menu")
    public List<Restaurant> getAllWithMenu() {
        return super.getAllWithMenu();
    }

    @GetMapping("/with-menu/by-date")
    public List<Restaurant> getAllWithMenuByDate(@RequestParam LocalDate registeredDate) {
        log.info("getAllWithMenuByDate");
        return super.getAllWithMenuByDate(registeredDate);
    }
}
