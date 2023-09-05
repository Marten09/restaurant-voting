package ru.marten.votingforrestaurants.web.vote;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.marten.votingforrestaurants.model.Vote;
import ru.marten.votingforrestaurants.service.RestaurantService;
import ru.marten.votingforrestaurants.service.VoteService;
import ru.marten.votingforrestaurants.to.VoteTo;
import ru.marten.votingforrestaurants.web.AuthUser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping(value = UserVoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserVoteRestController extends AbstractVoteController {
    static final String REST_URL = "/api/user/vote";
    private final RestaurantService restaurantService;
    private final VoteService voteService;

    @PostMapping("/{restId}")
    public ResponseEntity<VoteTo> makeVote(@AuthenticationPrincipal AuthUser authUser, @PathVariable int restId) {
        Vote vote = new Vote(null, LocalDate.now(), LocalTime.now().truncatedTo(ChronoUnit.MINUTES), authUser.getUser(), restaurantService.getWithMenu(restId));
        VoteTo created = super.makeVote(vote);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{restId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal AuthUser authUser, @PathVariable int restId) {
        Vote vote = voteService.getUserVote(authUser.id());
        vote.setRestaurant(restaurantService.getWithMenu(restId));
        super.update(vote);
    }
}
