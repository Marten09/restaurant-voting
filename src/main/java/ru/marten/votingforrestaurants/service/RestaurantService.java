package ru.marten.votingforrestaurants.service;

import ru.marten.votingforrestaurants.model.Restaurant;
import ru.marten.votingforrestaurants.repository.RestaurantRepository;

import static ru.marten.votingforrestaurants.util.ValidationUtil.checkNotFoundWithId;

public class RestaurantService {
    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }
}
