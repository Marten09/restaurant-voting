package ru.marten.votingforrestaurants.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.marten.votingforrestaurants.error.DataConflictException;
import ru.marten.votingforrestaurants.error.NotFoundException;
import ru.marten.votingforrestaurants.model.MenuItem;
import ru.marten.votingforrestaurants.model.Restaurant;
import ru.marten.votingforrestaurants.repository.MenuItemRepository;

import static ru.marten.votingforrestaurants.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final RestaurantService restaurantService;

    @CacheEvict(value = "menuitem", allEntries = true)
    public MenuItem create(MenuItem menuItem, int restId) {
        Assert.notNull(menuItem, "menuitem must not be null");
        if (!menuItem.isNew()) {
            throw new DataConflictException("such menuitem already exists");
        }
        menuItem.setRestaurant(restaurantService.getWithDishes(restId));
        return menuItemRepository.save(menuItem);
    }

    @CacheEvict(value = "menuitem", allEntries = true)
    public void update(MenuItem menuItem, int restId) {
        Assert.notNull(menuItem, "menuitem must not be null");
        Restaurant restaurant = restaurantService.getWithDishes(restId);
        menuItem.setRestaurant(restaurant);
        checkNotFoundWithId(menuItemRepository.save(menuItem), menuItem.id());
    }

    @CacheEvict(value = "menuitem", allEntries = true)
    public void delete(int restId, int id) {
        checkNotFoundWithId(menuItemRepository.delete(restId, id) != 0, id);
    }

    public MenuItem get(int restId, int id) {
        return menuItemRepository.get(restId, id).orElseThrow(() -> new NotFoundException("menuitem with " + id + " not found"));
    }
}