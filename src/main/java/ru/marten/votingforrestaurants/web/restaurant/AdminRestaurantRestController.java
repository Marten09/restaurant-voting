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
