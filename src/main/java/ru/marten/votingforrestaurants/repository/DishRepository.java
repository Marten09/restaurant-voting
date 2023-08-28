package ru.marten.votingforrestaurants.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.marten.votingforrestaurants.model.Dish;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restId")
    int delete(@Param("restId") int restId, @Param("id") int id);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restId")
    List<Dish> getAllByRestaurant(@Param("restId") int restId);

    @Query("SELECT d FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restId")
    Optional<Dish> get(@Param("restId") int restId, @Param("id") int id);
}
