package finalProject.model;

import finalProject.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
@Getter
@Setter
@Table (name = "t_categories")
public class Category extends BaseEntity {
    @Column (name = "name")
    private String name;

}
