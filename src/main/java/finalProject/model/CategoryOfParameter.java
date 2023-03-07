package finalProject.model;

import finalProject.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Getter
@Setter
@Table (name = "t_categoriesofparameter")
public class CategoryOfParameter extends BaseEntity {
    @Column (name = "name")
    private String name;
}
