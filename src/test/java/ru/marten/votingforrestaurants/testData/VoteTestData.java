package ru.marten.votingforrestaurants.testData;

import ru.marten.votingforrestaurants.MatcherFactory;
import ru.marten.votingforrestaurants.model.Vote;
import ru.marten.votingforrestaurants.to.VoteTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static ru.marten.votingforrestaurants.testData.RestaurantTestData.restaurant1;
import static ru.marten.votingforrestaurants.testData.RestaurantTestData.restaurant3;
import static ru.marten.votingforrestaurants.testData.UserTestData.*;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "restaurant", "user");
    public static final MatcherFactory.Matcher<VoteTo> VOTE_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(VoteTo.class, "voteTime");

    public static final int VOTE1_ID = 1;

    public static final Vote vote1 = new Vote(VOTE1_ID, LocalDate.now(), LocalTime.of(11, 0), user, restaurant1);
    public static final Vote vote2 = new Vote(VOTE1_ID + 1, LocalDate.now(), LocalTime.of(13, 0), admin, restaurant3);
    public static final Vote vote3 = new Vote(VOTE1_ID + 2, LocalDate.now().minusDays(1), LocalTime.of(12, 0), admin, restaurant1);

    public static Vote getNew() {
        return new Vote(null, LocalDate.now(), LocalTime.now(), newUser, restaurant3);
    }

    public static Vote getUpdated() {
        Vote updated = new Vote(vote1);
        updated.setVoteTime(LocalTime.now().truncatedTo(ChronoUnit.MINUTES));
        updated.setRestaurant(restaurant3);
        return updated;
    }
}
