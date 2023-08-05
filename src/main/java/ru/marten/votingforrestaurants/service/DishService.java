package ru.marten.votingforrestaurants.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.marten.votingforrestaurants.model.Dish;
import ru.marten.votingforrestaurants.repository.DishRepository;

import java.util.List;

import static ru.marten.votingforrestaurants.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {
    private final DishRepository dishRepository;

    public DishService(DishRepository repository) {
        this.dishRepository = repository;
    }

    public Dish create(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        return dishRepository.save(dish);
    }

    public void update(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        checkNotFoundWithId(dishRepository.save(dish), dish.id());
    }

    public void delete(int id) {
        checkNotFoundWithId(dishRepository.delete(id) != 0, id);
    }

    public Dish get(int id) {
        return checkNotFoundWithId(dishRepository.findById(id).orElse(null), id);
    }

    public List<Dish> getAll() {
        return dishRepository.findAll();
    }
}