package ru.marten.votingforrestaurants.model;

import java.time.LocalDate;

public class Vote extends AbstractBaseEntity {
    private LocalDate voteDate;

    private User user;
    private Restaurant restaurant;
    public Vote(Integer id, LocalDate voteDate, User user,  Restaurant restaurant) {
        super(id);
        this.voteDate = voteDate;
        this.user = user;
        this.restaurant = restaurant;
    }

    public LocalDate getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(LocalDate voteDate) {
        this.voteDate = voteDate;
    }
}
