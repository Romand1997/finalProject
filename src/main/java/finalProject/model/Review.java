package finalProject.model;

import finalProject.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "t_reviews")
@Getter
@Setter
public class Review extends BaseEntity {

    @Column (name = "content")
    private String content;

    @Column (name = "addingDateTime")
    private LocalDateTime localDateTime;

    @ManyToOne
    private User user;

    @ManyToOne
    private Item item;
}
