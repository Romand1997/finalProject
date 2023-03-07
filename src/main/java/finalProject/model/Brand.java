package finalProject.model;

import finalProject.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name = "t_brands")
public class Brand extends BaseEntity {
    @Column (name = "name")
    private String name;
}
