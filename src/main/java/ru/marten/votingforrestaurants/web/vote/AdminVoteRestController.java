package ru.marten.votingforrestaurants.web.vote;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.marten.votingforrestaurants.model.Vote;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminVoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminVoteRestController extends AbstractVoteController {
    static final String REST_URL = "/api/admin/votes";

    @Override
    @GetMapping("/by-user/{userId}")
    public Vote getUserVote(@PathVariable int userId) {
        return super.getUserVote(userId);
    }

    @Override
    @GetMapping
    public List<Vote> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/by-date")
    public List<Vote> getAllByDate(@RequestParam LocalDate voteDate) {
        return super.getAllByDate(voteDate);
    }

    @Override
    @GetMapping("/winner")
    public Integer getWinnerRestaurantId(@RequestParam LocalDate localDate) {
        return super.getWinnerRestaurantId(localDate);
    }
}
