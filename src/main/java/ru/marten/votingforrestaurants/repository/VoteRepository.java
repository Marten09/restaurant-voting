package ru.marten.votingforrestaurants.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.marten.votingforrestaurants.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {
    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId")
    Optional<Vote> getUserVote(@Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE  v.voteDate=:voteDate")
    List<Vote> getAllByDate(@Param("voteDate") LocalDate voteDate);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.voteDate=:voteDate")
    Optional<Vote> getByDate(@Param("userId") int userId, @Param("voteDate") LocalDate voteDate);

    @Query("SELECT v.restaurant.id FROM Vote v WHERE v.voteDate=:voteDate GROUP BY v.restaurant.id ORDER BY COUNT(v.restaurant.id) DESC LIMIT 1")
    Integer countVoteByDate(@Param("voteDate") LocalDate voteDate);
}
