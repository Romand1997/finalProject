package finalProject.model;

import finalProject.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "t_items")
@Getter
@Setter
public class Item extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price")
    private String price;

    @ManyToOne
    private SecondCategory secondCategory;

    @ManyToOne
    private Brand brand;

    @Column (name = "addingDateTime")
    private LocalDateTime addingDateTime;

    @ManyToMany(fetch = FetchType.LAZY)
    @Column (name = "parameters")
    private List<Parameter> parameters;


}
