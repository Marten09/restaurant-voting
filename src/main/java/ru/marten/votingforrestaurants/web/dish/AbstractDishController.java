package ru.marten.votingforrestaurants.web.dish;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.marten.votingforrestaurants.model.Dish;
import ru.marten.votingforrestaurants.service.DishService;
import ru.marten.votingforrestaurants.to.DishTo;

import java.util.List;

import static ru.marten.votingforrestaurants.util.validation.ValidationUtil.assureIdConsistent;
import static ru.marten.votingforrestaurants.util.validation.ValidationUtil.checkNew;

@Slf4j
public abstract class AbstractDishController {
    @Autowired
    private DishService service;

    public DishTo create(Dish dish) {
        log.info("create {} for restaurant {}", dish, dish.getRestaurant().id());
        checkNew(dish);
        return service.create(dish);
    }

    public void update(Dish dish, int id) {
        log.info("update {} ", dish);
        assureIdConsistent(dish, id);
        service.update(dish);
    }

    public void delete(int restId, int id) {
        log.info("delete dish {} for restaurant {}", id, restId);
        service.delete(restId, id);
    }

    public Dish get(int restId, int id) {
        log.info("get dish {} for restaurant {}", id, restId);
        return service.get(restId, id);
    }

    public List<Dish> getAllByRestaurant(int restId) {
        log.info("getAll for restaurant {}", restId);
        return service.getAllByRestaurant(restId);
    }
}
