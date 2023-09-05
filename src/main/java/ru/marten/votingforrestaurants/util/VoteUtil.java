package ru.marten.votingforrestaurants.util;

import lombok.experimental.UtilityClass;
import ru.marten.votingforrestaurants.model.Vote;
import ru.marten.votingforrestaurants.to.VoteTo;

@UtilityClass
public class VoteUtil {
    public static VoteTo createTo(Vote vote) {
        return new VoteTo(vote.getId(), vote.getRestaurant().getId());
    }
}
