package ru.marten.votingforrestaurants.model;

import java.time.LocalDate;

public class Dish extends AbstractBaseEntity {
    private String description;
    private int price;
    private LocalDate registered;
    private Restaurant restaurant;

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
