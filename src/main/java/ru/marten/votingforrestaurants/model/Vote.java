package ru.marten.votingforrestaurants.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "vote", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "vote_date"}, name = "vote_unique_user_day_idx")
})
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Vote extends BaseEntity implements Serializable {
    @Column(name = "vote_date", nullable = false)
    @NotNull
    private LocalDate voteDate;

    @Column(name = "vote_time", nullable = false)
    @NotNull
    private LocalTime voteTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public Vote(Vote vote) {
        this(vote.id, vote.voteDate, vote.voteTime, vote.user, vote.restaurant);
    }

    public Vote(Integer id, LocalDate voteDate, LocalTime voteTime, User user, Restaurant restaurant) {
        super(id);
        this.voteDate = voteDate;
        this.voteTime = voteTime;
        this.user = user;
        this.restaurant = restaurant;
    }
}
