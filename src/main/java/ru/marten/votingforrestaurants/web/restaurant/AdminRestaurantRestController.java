package ru.marten.votingforrestaurants.web.restaurant;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.marten.votingforrestaurants.model.Restaurant;
import ru.marten.votingforrestaurants.to.RestaurantTo;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantRestController extends AbstractRestaurantController {
    static final String REST_URL = "/api/admin/restaurants";

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@Valid @RequestBody Restaurant restaurant) {
        Restaurant created = super.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id) {
        super.update(restaurant, id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

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

    @GetMapping("/with-dishes")
    public List<Restaurant> getAllWithDishes() {
        return super.getAllWithDishes();
    }

    @GetMapping("/with-dishes/by-date")
    public List<Restaurant> getAllWithDishesByDate(@RequestParam LocalDate menuDate) {
        return super.getAllWithDishesByDate(menuDate);
    }
}
