package ru.marten.votingforrestaurants.model;

import java.util.List;

public class Restaurant extends AbstractBaseEntity {
    private String name;
    private List<Dish> menuList;

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
