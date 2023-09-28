package ru.marten.votingforrestaurants.web.vote;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.marten.votingforrestaurants.model.Vote;
import ru.marten.votingforrestaurants.service.RestaurantService;
import ru.marten.votingforrestaurants.service.VoteService;
import ru.marten.votingforrestaurants.to.VoteTo;
import ru.marten.votingforrestaurants.util.VoteUtil;
import ru.marten.votingforrestaurants.web.AuthUser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class VoteRestController {
    private final Logger log = getLogger(getClass());

    static final String REST_URL = "/api/user/vote";
    private final RestaurantService restaurantService;
    private final VoteService voteService;

    @PostMapping()
    public ResponseEntity<VoteTo> makeVote(@AuthenticationPrincipal AuthUser authUser, @RequestParam int restId) {
        log.info("makeVote");
        Vote vote = new Vote(null, LocalDate.now(), LocalTime.now().truncatedTo(ChronoUnit.MINUTES), authUser.getUser(), restaurantService.getWithDishes(restId));
        VoteTo created = voteService.makeVote(vote);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal AuthUser authUser, @RequestParam int restId) {
        log.info("update vote");
        Vote vote = voteService.getByUser(authUser.id());
        voteService.update(vote, restId);
    }

    @GetMapping()
    public VoteTo get(@AuthenticationPrincipal AuthUser authUser) {
        log.info("get vote");
        return VoteUtil.createTo(voteService.getByUser(authUser.id()));
    }

    @GetMapping(value = "/all-votes")
    public List<VoteTo> getAllByUser(@AuthenticationPrincipal AuthUser authUser) {
        log.info("get all votes");
        return VoteUtil.getTos(voteService.getAllByUser(authUser.id()));
    }
}
