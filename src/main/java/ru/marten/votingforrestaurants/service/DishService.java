package ru.marten.votingforrestaurants.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.marten.votingforrestaurants.model.Dish;
import ru.marten.votingforrestaurants.repository.DishRepository;
import ru.marten.votingforrestaurants.to.DishTo;
import ru.marten.votingforrestaurants.util.DishUtil;

import java.util.List;

import static ru.marten.votingforrestaurants.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
@Transactional(readOnly = true)
public class DishService {
    private final DishRepository dishRepository;

    public DishService(DishRepository repository) {
        this.dishRepository = repository;
    }

    @Transactional
    public DishTo create(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        if (!dish.isNew()) {
            return null;
        }
        Dish newDish = dishRepository.save(dish);
        return DishUtil.createTo(newDish);
    }

    public void update(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        checkNotFoundWithId(dishRepository.save(dish), dish.id());
    }

    @Transactional
    public void delete(int restId, int id) {
        checkNotFoundWithId(dishRepository.delete(restId, id) != 0, id);
    }

    public Dish get(int restId, int id) {
        return checkNotFoundWithId(dishRepository.get(restId, id).orElse(null), id);
    }

    public List<Dish> getAllByRestaurant(int restId) {
        return checkNotFoundWithId(dishRepository.getAllByRestaurant(restId), restId);
    }
}