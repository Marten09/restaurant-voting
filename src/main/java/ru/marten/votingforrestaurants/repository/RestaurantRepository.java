package ru.marten.votingforrestaurants.repository;

import ru.marten.votingforrestaurants.model.Restaurant;

public interface RestaurantRepository {
    Restaurant get(int id);
}
