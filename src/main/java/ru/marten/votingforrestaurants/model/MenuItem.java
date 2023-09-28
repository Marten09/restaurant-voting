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

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "menu_item", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"restaurant_id", "menu_date", "description"}, name = "menu_item_unique_menu_day_idx")
})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuItem extends BaseEntity implements Serializable {

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    private String description;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "menu_date", nullable = false)
    @NotNull
    private LocalDate menuDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Restaurant restaurant;

    public MenuItem(MenuItem menuItem) {
        this(menuItem.id, menuItem.description, menuItem.price, menuItem.menuDate);
    }

    public MenuItem(Integer id, String description, int price, LocalDate menuDate) {
        super(id);
        this.description = description;
        this.price = price;
        this.menuDate = menuDate;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", menuDate=" + menuDate +
                ", restaurant=" + restaurant +
                ", id=" + id +
                '}';
    }
}
