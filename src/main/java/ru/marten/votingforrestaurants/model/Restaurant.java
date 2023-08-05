package ru.marten.votingforrestaurants.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractBaseEntity {
    @Column(name = "name", nullable = false)
    @NotNull
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("registered DESC")
    @OnDelete(action = OnDeleteAction.CASCADE) //https://stackoverflow.com/a/44988100/548473
    private List<Dish> menuList;

    public Restaurant() {

    }

    public Restaurant(Restaurant restaurant) {
        this(restaurant.id, restaurant.name, restaurant.menuList);
    }

    public Restaurant(Integer id, String name, List<Dish> menuList) {
        super(id);
        this.name = name;
        this.menuList = menuList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dish> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Dish> menuList) {
        this.menuList = menuList;
    }
}
