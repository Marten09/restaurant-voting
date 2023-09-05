package ru.marten.votingforrestaurants.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.marten.votingforrestaurants.model.Vote;
import ru.marten.votingforrestaurants.service.VoteService;
import ru.marten.votingforrestaurants.to.VoteTo;

import java.time.LocalDate;
import java.util.List;

import static ru.marten.votingforrestaurants.util.validation.ValidationUtil.checkNew;

public class AbstractVoteController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    public Vote getUserVote(int userId) {
        log.info("getUserVote");
        return service.getUserVote(userId);
    }

    public VoteTo makeVote(Vote vote) {
        checkNew(vote);
        return service.makeVote(vote);
    }

    public void update(Vote vote) {
        service.update(vote);
    }

    public List<Vote> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public List<Vote> getAllByDate(LocalDate voteDate) {
        log.info("getAllByDate {} ", voteDate);
        return service.getAllByDate(voteDate);
    }

    public Integer getWinnerRestaurantId(LocalDate localDate) {
        return service.getWinnerRestaurantId(localDate);
    }
}
