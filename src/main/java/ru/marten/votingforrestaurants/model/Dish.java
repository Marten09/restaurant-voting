package ru.marten.votingforrestaurants.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "dish")
public class Dish extends AbstractBaseEntity {
    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    private String description;
    @Column(name = "price", nullable = false)
    @Range(min = 10, max = 5000)
    private int price;
    @Column(name = "registered", nullable = false)
    @NotNull
    private LocalDate registered;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(Dish dish) {
        this(dish.id, dish.description, dish.price, dish.registered, dish.restaurant);
    }

    public Dish(Integer id, String description, int price, LocalDate registered, Restaurant restaurant) {
        super(id);
        this.description = description;
        this.price = price;
        this.registered = registered;
        this.restaurant = restaurant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDate registered) {
        this.registered = registered;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
