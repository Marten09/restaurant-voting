package ru.marten.votingforrestaurants.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.marten.votingforrestaurants.model.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @EntityGraph(attributePaths = {"menuList"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r WHERE r.id=?1")
    Restaurant getWithDishes(int id);

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.menuList m WHERE m.menuDate=:menuDate AND r.id=:id")
    Optional<Restaurant> getWithDishesByDate(@Param("id") int id, @Param("menuDate") LocalDate menuDate);

    @EntityGraph(attributePaths = {"menuList"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r ")
    List<Restaurant> getAllWithDishes();

    @Query("SELECT r FROM Restaurant r JOIN FETCH r.menuList m WHERE m.menuDate=:menuDate")
    List<Restaurant> getAllWithDishesByDate(@Param("menuDate") LocalDate menuDate);
}
