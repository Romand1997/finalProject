package finalProject.model;

import finalProject.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "t_orders")
@Getter
@Setter
public class Order extends BaseEntity {

    @ManyToMany
    private List<Item> items;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
