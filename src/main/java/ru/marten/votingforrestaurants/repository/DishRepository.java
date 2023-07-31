package ru.marten.votingforrestaurants.repository;

import ru.marten.votingforrestaurants.model.Dish;

import java.util.List;

public interface DishRepository {
    Dish save(Dish dish, int userId);

    boolean delete(int id, int userId);

    Dish get(int id, int userId);

    List<Dish> getAll(int userId);
}
