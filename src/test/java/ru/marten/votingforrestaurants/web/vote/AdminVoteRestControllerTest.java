package ru.marten.votingforrestaurants.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.marten.votingforrestaurants.testData.VoteTestData;
import ru.marten.votingforrestaurants.web.AbstractControllerTest;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.marten.votingforrestaurants.testData.UserTestData.ADMIN_MAIL;
import static ru.marten.votingforrestaurants.testData.UserTestData.USER_ID;
import static ru.marten.votingforrestaurants.testData.VoteTestData.VOTE_MATCHER;
import static ru.marten.votingforrestaurants.testData.VoteTestData.vote1;

class AdminVoteRestControllerTest extends AbstractControllerTest {
    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getUserVote() throws Exception {
        perform(MockMvcRequestBuilders.get(AdminVoteRestController.REST_URL + "/by-user/" + USER_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(vote1));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(AdminVoteRestController.REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(VoteTestData.getAll()));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAllByDate() throws Exception {
        perform(MockMvcRequestBuilders.get(AdminVoteRestController.REST_URL + "/by-date")
                .param("voteDate", String.valueOf(LocalDate.now())))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(VoteTestData.getAll()));
    }
}