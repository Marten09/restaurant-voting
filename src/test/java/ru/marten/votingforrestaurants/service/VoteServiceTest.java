package ru.marten.votingforrestaurants.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.marten.votingforrestaurants.error.IllegalRequestDataException;
import ru.marten.votingforrestaurants.model.Vote;
import ru.marten.votingforrestaurants.to.VoteTo;
import ru.marten.votingforrestaurants.util.VoteUtil;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.marten.votingforrestaurants.service.VoteService.DEADLINE;
import static ru.marten.votingforrestaurants.testData.RestaurantTestData.RESTAURANT1_ID;
import static ru.marten.votingforrestaurants.testData.UserTestData.USER_ID;
import static ru.marten.votingforrestaurants.testData.VoteTestData.*;

class VoteServiceTest extends AbstractServiceTest {
    @Autowired
    private VoteService service;

    @Test
    void get() {
        Vote actual = service.get(VOTE1_ID);
        VOTE_MATCHER.assertMatch(actual, vote1);
    }

    @Test
    void getUserVote() {
        Vote actual = service.getByUser(USER_ID);
        VOTE_MATCHER.assertMatch(actual, vote1);
    }

    @Test
    void makeVote() {
        VoteTo created = service.makeVote(getNew());
        int newId = created.id();
        VoteTo newTo = VoteUtil.createTo(getNew());
        newTo.setId(newId);
        VOTE_TO_MATCHER.assertMatch(created, newTo);
    }

    @Test
    void update() {
        Vote updated = getUpdated();
        if (updated.getVoteTime().isAfter(DEADLINE)) {
            assertThrows(IllegalRequestDataException.class, () -> {
                service.update(updated, RESTAURANT1_ID);
            });
        } else {
            service.update(updated, RESTAURANT1_ID);
            VOTE_MATCHER.assertMatch(service.get(VOTE1_ID), getUpdated());
        }
    }
}