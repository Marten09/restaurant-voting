package ru.marten.votingforrestaurants.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.marten.votingforrestaurants.model.MenuItem;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface MenuItemRepository extends BaseRepository<MenuItem> {
    @Transactional
    @Modifying
    @Query("DELETE FROM MenuItem d WHERE d.id=:id AND d.restaurant.id=:restId")
    int delete(@Param("restId") int restId, @Param("id") int id);

    @Query("SELECT d FROM MenuItem d WHERE d.id=:id AND d.restaurant.id=:restId")
    Optional<MenuItem> get(@Param("restId") int restId, @Param("id") int id);
}
