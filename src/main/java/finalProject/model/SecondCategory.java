package finalProject.model;

import finalProject.BaseEntity;
import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table (name = "t_secondcategories")
public class SecondCategory extends BaseEntity {
    @Column (name = "name")
    private String name;
    @ManyToOne
    private Category category;
    @ManyToMany
    private List<CategoryOfParameter> categoryOfParameters;
}
