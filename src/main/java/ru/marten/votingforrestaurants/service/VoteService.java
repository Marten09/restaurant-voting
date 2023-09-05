package ru.marten.votingforrestaurants.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.marten.votingforrestaurants.error.IllegalRequestDataException;
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

    public static final String ALREADY_VOTED_EXCEPTION = "You have already voted today";
    public static final String CANNOT_BE_CHANGED_EXCEPTION = "The vote cannot be changed today";

    public static final LocalTime DEADLINE = LocalTime.of(11, 0);

    public Vote get(int id) {
        return voteRepository.findById(id).orElse(null);
    }

    public Vote getUserVote(int userId) {
        return voteRepository.getUserVote(userId).orElseThrow();
    }

    public VoteTo makeVote(Vote vote) {
        if (isVoteToday(vote.getUser().id())) {
            throw new IllegalRequestDataException(ALREADY_VOTED_EXCEPTION);
        }
        Vote newVote = voteRepository.save(vote);
        return VoteUtil.createTo(newVote);
    }

    public void update(Vote vote) {
        vote.setVoteTime(LocalTime.now().truncatedTo(ChronoUnit.MINUTES));
        if (vote.getVoteTime().isAfter(DEADLINE)) {
            throw new IllegalRequestDataException(CANNOT_BE_CHANGED_EXCEPTION);
        }
        Vote updated = checkNotFoundWithId(voteRepository.save(vote), vote.id());
        VoteUtil.createTo(updated);
    }

    public List<Vote> getAll() {
        return voteRepository.findAll();
    }

    public List<Vote> getAllByDate(LocalDate voteDate) {
        return voteRepository.getAllByDate(voteDate);
    }

    public boolean isVoteToday(int userId) {
        return voteRepository.getByDate(userId, LocalDate.now()).isPresent();
    }

    public Integer getWinnerRestaurantId(LocalDate localDate) {
        return voteRepository.countVoteByDate(localDate);
    }
}
