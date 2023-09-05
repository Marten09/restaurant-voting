package ru.marten.votingforrestaurants.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.marten.votingforrestaurants.model.Dish;
import ru.marten.votingforrestaurants.repository.DishRepository;

import java.util.List;

import static ru.marten.votingforrestaurants.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class DishService {
    private final DishRepository dishRepository;

    @CacheEvict(value = "dishes", allEntries = true)
    public Dish create(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        if (!dish.isNew()) {
            return null;
        }
        return dishRepository.save(dish);
    }

    @CacheEvict(value = "dishes", allEntries = true)
    public void update(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        checkNotFoundWithId(dishRepository.save(dish), dish.id());
    }

    @CacheEvict(value = "dishes", allEntries = true)
    public void delete(int restId, int id) {
        checkNotFoundWithId(dishRepository.delete(restId, id) != 0, id);
    }

    public Dish get(int restId, int id) {
        return checkNotFoundWithId(dishRepository.get(restId, id).orElse(null), id);
    }

    @Cacheable("dishes")
    public List<Dish> getAllByRestaurant(int restId) {
        return checkNotFoundWithId(dishRepository.getAllByRestaurant(restId), restId);
    }
}