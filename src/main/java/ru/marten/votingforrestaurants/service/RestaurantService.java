package ru.marten.votingforrestaurants.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.marten.votingforrestaurants.model.Restaurant;
import ru.marten.votingforrestaurants.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    public Restaurant get(int id) {
        return repository.findById(id).orElse(null);
    }

    public Restaurant getWithMenu(int id) {
        return repository.getWithMenu(id);
    }

    public Restaurant getWithMenuByDate(int id, LocalDate registeredDate) {
        return repository.getWithMenuByDate(id, registeredDate);
    }

    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    public List<Restaurant> getAllWithMenu() {
        return repository.getAllWithMenu();
    }

    public List<Restaurant> getAllWithMenuByDate(LocalDate registeredDate) {
        return repository.getAllWithMenuByDate(registeredDate);
    }
}
