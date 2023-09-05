package ru.marten.votingforrestaurants.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
@AllArgsConstructor
public class RestaurantService {
    private final RestaurantRepository repository;

    @CacheEvict(value = "restaurants", allEntries = true)
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.id());
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @Cacheable(value = "restaurants")
    public Restaurant getWithMenu(int id) {
        return checkNotFoundWithId(repository.getWithMenu(id), id);
    }

    @Cacheable(value = "restaurants")
    public Restaurant getWithMenuByDate(int id, LocalDate registeredDate) {
        return checkNotFoundWithId(repository.getWithMenuByDate(id, registeredDate), id).orElseThrow();
    }

    @Cacheable(value = "restaurants")
    public List<RestaurantTo> getAll() {
        return RestaurantUtil.getTos(repository.findAll());
    }

    @Cacheable(value = "restaurants")
    public List<Restaurant> getAllWithMenu() {
        return repository.getAllWithMenu();
    }

    @Cacheable(value = "restaurants")
    public List<Restaurant> getAllWithMenuByDate(LocalDate registeredDate) {
        return repository.getAllWithMenuByDate(registeredDate);
    }
}
