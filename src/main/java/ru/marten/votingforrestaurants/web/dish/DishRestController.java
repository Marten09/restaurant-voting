package ru.marten.votingforrestaurants.web.dish;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.marten.votingforrestaurants.model.Dish;
import ru.marten.votingforrestaurants.model.Restaurant;
import ru.marten.votingforrestaurants.service.RestaurantService;
import ru.marten.votingforrestaurants.to.DishTo;
import ru.marten.votingforrestaurants.util.DishUtil;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class DishRestController extends AbstractDishController {
    static final String REST_URL = "/api/admin/restaurants";
    private final RestaurantService restaurantService;

    @PostMapping(value = "/{restId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody DishTo dishTo, @PathVariable int restId) {
        Dish dish = DishUtil.createNewFromTo(dishTo);
        dish.setRestaurant(restaurantService.getWithMenu(restId));
        Dish created = super.create(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restId}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{restId}/dishes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody DishTo dishTo, @PathVariable int restId, @PathVariable int id) {
        Dish dish = DishUtil.createNewFromTo(dishTo);
        Restaurant restaurant = restaurantService.getWithMenu(restId);
        dish.setRestaurant(restaurant);
        super.update(dish, id);
    }

    @Override
    @DeleteMapping("/{restId}/dishes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restId, @PathVariable int id) {
        super.delete(restId, id);
    }

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
