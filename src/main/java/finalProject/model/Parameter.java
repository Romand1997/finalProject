package finalProject.model;

import finalProject.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "t_parameters")
@Getter
@Setter
public class Parameter extends BaseEntity {
    @Column (name = "name")
    private String name;
    @ManyToOne
    private CategoryOfParameter categoryOfParameter;
}
