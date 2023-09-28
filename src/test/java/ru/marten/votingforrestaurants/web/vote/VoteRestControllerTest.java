package ru.marten.votingforrestaurants.web.vote;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.marten.votingforrestaurants.model.Vote;
import ru.marten.votingforrestaurants.service.VoteService;
import ru.marten.votingforrestaurants.util.JsonUtil;
import ru.marten.votingforrestaurants.util.VoteUtil;
import ru.marten.votingforrestaurants.web.AbstractControllerTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.marten.votingforrestaurants.service.VoteService.DEADLINE;
import static ru.marten.votingforrestaurants.testData.RestaurantTestData.*;
import static ru.marten.votingforrestaurants.testData.UserTestData.*;
import static ru.marten.votingforrestaurants.testData.VoteTestData.*;

class VoteRestControllerTest extends AbstractControllerTest {
    @Autowired
    private VoteService service;

    @Test
    @WithUserDetails(value = NEW_USER_MAIL)
    void makeVote() throws Exception {
        Vote newVote = new Vote(LocalDate.now(), LocalTime.now().truncatedTo(ChronoUnit.MINUTES),
                newUser, restaurant1);
        ResultActions action = perform(MockMvcRequestBuilders.post(VoteRestController.REST_URL)
                .param("restId", String.valueOf(RESTAURANT1_ID))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote)))
                .andExpect(status().isCreated());
        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(service.getByUser(NEW_USER_ID), newVote);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void makeVoteAgain() throws Exception {
        Vote newVote = new Vote(LocalDate.now(), LocalTime.now().truncatedTo(ChronoUnit.MINUTES),
                user, restaurant1);
        perform(MockMvcRequestBuilders.post(VoteRestController.REST_URL)
                .param("restId", String.valueOf(RESTAURANT1_ID))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.detail").value(VoteService.ALREADY_VOTED_EXCEPTION));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void update() throws Exception {
        Vote updated = new Vote(VOTE1_ID, LocalDate.now(), LocalTime.now().truncatedTo(ChronoUnit.MINUTES), user, restaurant3);
        if (updated.getVoteTime().isAfter(DEADLINE)) {
            perform(MockMvcRequestBuilders.put(VoteRestController.REST_URL)
                    .param("restId", String.valueOf(restaurant3.id()))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.writeValue(updated)))
                    .andDo(print())
                    .andExpect(status().isUnprocessableEntity())
                    .andExpect(jsonPath("$.detail").value(VoteService.CANNOT_BE_CHANGED_EXCEPTION));
        } else {
            perform(MockMvcRequestBuilders.put(VoteRestController.REST_URL)
                    .param("restId", String.valueOf(restaurant3.id()))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.writeValue(updated)))
                    .andDo(print())
                    .andExpect(status().isNoContent());
            Vote newVOte = service.getByUser(USER_ID);
            VOTE_MATCHER.assertMatch(newVOte, updated);
        }
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateAfterDeadLine() throws Exception {
        Assumptions.assumeTrue(LocalTime.now().isAfter(DEADLINE));
        Vote updated = new Vote(VOTE1_ID, LocalDate.now(), LocalTime.now().truncatedTo(ChronoUnit.MINUTES), user, restaurant3);
        perform(MockMvcRequestBuilders.put(VoteRestController.REST_URL)
                .param("restId", String.valueOf(restaurant3.id()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.detail").value(VoteService.CANNOT_BE_CHANGED_EXCEPTION));

    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateBeforeDeadLine() throws Exception {
        Assumptions.assumeTrue(LocalTime.now().isBefore(DEADLINE));
        Vote updated = new Vote(VOTE1_ID, LocalDate.now(), LocalTime.now().truncatedTo(ChronoUnit.MINUTES), user, restaurant3);
        perform(MockMvcRequestBuilders.put(VoteRestController.REST_URL)
                .param("restId", String.valueOf(restaurant3.id()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());
        Vote newVOte = service.getByUser(USER_ID);
        VOTE_MATCHER.assertMatch(newVOte, updated);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(VoteRestController.REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_TO_MATCHER.contentJson(VoteUtil.createTo(vote1)));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAllByUser() throws Exception {
        perform(MockMvcRequestBuilders.get(VoteRestController.REST_URL + "/all-votes"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_TO_MATCHER.contentJson(VoteUtil.getTos(List.of(vote1))));
    }
}