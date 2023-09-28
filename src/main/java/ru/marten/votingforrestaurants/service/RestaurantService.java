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
    public Restaurant getWithDishes(int id) {
        return checkNotFoundWithId(repository.getWithDishes(id), id);
    }

    @Cacheable(value = "restaurants")
    public Restaurant getWithDishesByDate(int id, LocalDate menuDate) {
        return checkNotFoundWithId(repository.getWithDishesByDate(id, menuDate), id).orElseThrow();
    }

    @Cacheable(value = "restaurants")
    public List<RestaurantTo> getAll() {
        return RestaurantUtil.getTos(repository.findAll());
    }

    public List<Restaurant> getAllWithDishes() {
        return repository.getAllWithDishes();
    }

    @Cacheable(value = "restaurants")
    public List<Restaurant> getAllWithDishesByDate(LocalDate menuDate) {
        return repository.getAllWithDishesByDate(menuDate);
    }
}
