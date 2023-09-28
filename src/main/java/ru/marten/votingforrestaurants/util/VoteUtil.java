package ru.marten.votingforrestaurants.util;

import lombok.experimental.UtilityClass;
import ru.marten.votingforrestaurants.model.Vote;
import ru.marten.votingforrestaurants.to.VoteTo;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class VoteUtil {
    public static VoteTo createTo(Vote vote) {
        return new VoteTo(vote.getId(), vote.getRestaurant().getId(), vote.getVoteDate(), vote.getVoteTime());
    }

    public List<VoteTo> getTos(List<Vote> votes) {
        return votes.stream().map(VoteUtil::createTo).collect(Collectors.toList());
    }
}
