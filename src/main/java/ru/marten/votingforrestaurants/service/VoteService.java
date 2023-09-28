package ru.marten.votingforrestaurants.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.marten.votingforrestaurants.error.IllegalRequestDataException;
import ru.marten.votingforrestaurants.error.NotFoundException;
import ru.marten.votingforrestaurants.model.Vote;
import ru.marten.votingforrestaurants.repository.VoteRepository;
import ru.marten.votingforrestaurants.to.VoteTo;
import ru.marten.votingforrestaurants.util.VoteUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static ru.marten.votingforrestaurants.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final RestaurantService restaurantService;

    public static final String ALREADY_VOTED_EXCEPTION = "You have already voted today";
    public static final String CANNOT_BE_CHANGED_EXCEPTION = "The vote cannot be changed today";

    public static final LocalTime DEADLINE = LocalTime.of(11, 0);

    public Vote get(int id) {
        return voteRepository.findById(id).orElseThrow(() -> new NotFoundException("Vote with " + id + " not found"));
    }

    public Vote getByUser(int userId) {
        return voteRepository.getByDate(userId, LocalDate.now())
                .orElseThrow(() -> new NotFoundException("The vote was not found, you have not done your vote yet"));
    }

    @Transactional
    public VoteTo makeVote(Vote vote) {
        if (isVoteToday(vote.getUser().id())) {
            throw new IllegalRequestDataException(ALREADY_VOTED_EXCEPTION);
        }
        Vote newVote = voteRepository.save(vote);
        return VoteUtil.createTo(newVote);
    }

    @Transactional
    public void update(Vote vote, int restId) {
        vote.setRestaurant(restaurantService.getWithDishes(restId));
        vote.setVoteTime(LocalTime.now().truncatedTo(ChronoUnit.MINUTES));
        if (vote.getVoteTime().isAfter(DEADLINE)) {
            throw new IllegalRequestDataException(CANNOT_BE_CHANGED_EXCEPTION);
        }
        Vote updated = checkNotFoundWithId(voteRepository.save(vote), vote.id());
        VoteUtil.createTo(updated);
    }

    public List<Vote> getAllByUser(int userId) {
        return voteRepository.getAllByUser(userId);
    }

    public boolean isVoteToday(int userId) {
        return voteRepository.getByDate(userId, LocalDate.now()).isPresent();
    }
}
