package ru.marten.votingforrestaurants.web.restaurant;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.marten.votingforrestaurants.model.Restaurant;
import ru.marten.votingforrestaurants.service.RestaurantService;
import ru.marten.votingforrestaurants.to.RestaurantTo;

import java.time.LocalDate;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.marten.votingforrestaurants.util.validation.ValidationUtil.assureIdConsistent;
import static ru.marten.votingforrestaurants.util.validation.ValidationUtil.checkNew;

public abstract class AbstractRestaurantController {
    private final Logger log = getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    public Restaurant create(Restaurant restaurant) {
        log.info("create {} ", restaurant);
        checkNew(restaurant);
        return restaurantService.create(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update {} by id {}", restaurant, id);
        assureIdConsistent(restaurant, id);
        restaurantService.update(restaurant);
    }

    public void delete(int id) {
        log.info("delete by id {} ", id);
        restaurantService.delete(id);
    }

    public Restaurant getWithDishes(int id) {
        log.info("getWithDishes {}", id);
        return restaurantService.getWithDishes(id);
    }

    public Restaurant getWithDishesByDate(int id, LocalDate menuDate) {
        log.info("getWithDishesByDate {} {}", id, menuDate);
        return restaurantService.getWithDishesByDate(id, menuDate);
    }

    public List<RestaurantTo> getAll() {
        log.info("getAll");
        return restaurantService.getAll();
    }

    public List<Restaurant> getAllWithDishes() {
        log.info("getAllWithDishes");
        return restaurantService.getAllWithDishes();
    }

    public List<Restaurant> getAllWithDishesByDate(LocalDate menuDate) {
        log.info("getAllWithDishesByDate");
        return restaurantService.getAllWithDishesByDate(menuDate);
    }
}
