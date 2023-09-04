package ru.marten.votingforrestaurants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "dish", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"description", "registered", "restaurant_id"}, name = "dish_unique_menu_day_idx")
})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dish extends BaseEntity implements Serializable {
    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    private String description;
    @Column(name = "price", nullable = false)
    @Range(min = 10, max = 5000)
    private int price;
    @Column(name = "registered", nullable = false)
    @NotNull
    @JsonIgnore
    private LocalDate registered;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
//    @NotNull
    private Restaurant restaurant;

    public Dish(Dish dish) {
        this(dish.id, dish.description, dish.price, dish.registered);
    }

    public Dish(Integer id, String description, int price, LocalDate registered) {
        super(id);
        this.description = description;
        this.price = price;
        this.registered = registered;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", registered=" + registered +
                ", restaurant=" + restaurant +
                ", id=" + id +
                '}';
    }
}
