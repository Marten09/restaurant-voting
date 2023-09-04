package ru.marten.votingforrestaurants.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.marten.votingforrestaurants.model.Restaurant;
import ru.marten.votingforrestaurants.repository.RestaurantRepository;
import ru.marten.votingforrestaurants.to.RestaurantTo;
import ru.marten.votingforrestaurants.util.RestaurantUtil;

import java.time.LocalDate;
import java.util.List;

import static ru.marten.votingforrestaurants.util.validation.ValidationUtil.checkNotFoundWithId;

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

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.id());
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    public Restaurant getWithMenu(int id) {
        return checkNotFoundWithId(repository.getWithMenu(id), id);
    }

    public Restaurant getWithMenuByDate(int id, LocalDate registeredDate) {
        return checkNotFoundWithId(repository.getWithMenuByDate(id, registeredDate), id).orElseThrow();
    }

    public List<RestaurantTo> getAll() {
        return RestaurantUtil.getTos(repository.findAll());
    }

    public List<Restaurant> getAllWithMenu() {
        return repository.getAllWithMenu();
    }

    public List<Restaurant> getAllWithMenuByDate(LocalDate registeredDate) {
        return repository.getAllWithMenuByDate(registeredDate);
    }
}
