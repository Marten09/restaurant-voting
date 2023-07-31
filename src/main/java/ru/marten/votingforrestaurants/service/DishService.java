package ru.marten.votingforrestaurants.service;

import org.springframework.util.Assert;
import ru.marten.votingforrestaurants.model.Dish;
import ru.marten.votingforrestaurants.repository.DishRepository;

import java.util.List;

import static ru.marten.votingforrestaurants.util.ValidationUtil.checkNotFoundWithId;

public class DishService {
    private final DishRepository repository;

    public DishService(DishRepository repository) {
        this.repository = repository;
    }

    public Dish create(Dish dish, int userId) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish, userId);
    }

    public Dish update(Dish dish, int userId) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish, userId);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public Dish get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public List<Dish> getAll(int userId) {
        return repository.getAll(userId);
    }
}
