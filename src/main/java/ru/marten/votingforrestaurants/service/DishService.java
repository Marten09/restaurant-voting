package ru.marten.votingforrestaurants.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.marten.votingforrestaurants.model.Dish;
import ru.marten.votingforrestaurants.repository.DishRepository;
import ru.marten.votingforrestaurants.repository.RestaurantRepository;

import java.util.List;

import static ru.marten.votingforrestaurants.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
@Transactional(readOnly = true)
public class DishService {
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    public DishService(DishRepository repository, RestaurantRepository restaurantRepository) {
        this.dishRepository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    public Dish create(Dish dish, int restId) {
        Assert.notNull(dish, "dish must not be null");
        if (!dish.isNew() && get(dish.id(), restId) == null) {
            return null;
        }
        dish.setRestaurant(restaurantRepository.getReferenceById(restId));
        return dishRepository.save(dish);
    }

    public void update(Dish dish, int restId) {
        Assert.notNull(dish, "dish must not be null");
        dish.setRestaurant(restaurantRepository.getReferenceById(restId));
        checkNotFoundWithId(dishRepository.save(dish), dish.id());
    }

    @Transactional
    public void delete(int id, int restId) {
        checkNotFoundWithId(dishRepository.delete(id, restId) != 0, id);
    }

    public Dish get(int id, int restId) {
        return checkNotFoundWithId(dishRepository.findById(id)
                .filter(dish -> dish.getRestaurant().getId() == restId)
                .orElse(null), id);
    }

    public List<Dish> getAll(int restId) {
        return dishRepository.getAll(restId);
    }
}