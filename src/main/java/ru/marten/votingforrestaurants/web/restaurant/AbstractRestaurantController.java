package ru.marten.votingforrestaurants.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.marten.votingforrestaurants.model.Restaurant;
import ru.marten.votingforrestaurants.service.RestaurantService;
import ru.marten.votingforrestaurants.to.RestaurantTo;

import java.time.LocalDate;
import java.util.List;

import static ru.marten.votingforrestaurants.util.validation.ValidationUtil.assureIdConsistent;
import static ru.marten.votingforrestaurants.util.validation.ValidationUtil.checkNew;

public abstract class AbstractRestaurantController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

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

    public Restaurant getWithMenu(int id) {
        log.info("getWithMenu {}", id);
        return restaurantService.getWithMenu(id);
    }

    public Restaurant getWithMenuByDate(int id, LocalDate registeredDate) {
        log.info("getWithMenu {} {}", id, registeredDate);
        return restaurantService.getWithMenuByDate(id, registeredDate);
    }

    public List<RestaurantTo> getAll() {
        log.info("getAll");
        return restaurantService.getAll();
    }

    public List<Restaurant> getAllWithMenu() {
        log.info("getAllWithMenu");
        return restaurantService.getAllWithMenu();
    }

    public List<Restaurant> getAllWithMenuByDate(LocalDate registeredDate) {
        log.info("getAllWithMenuByDate");
        return restaurantService.getAllWithMenuByDate(registeredDate);
    }
}
