package finalProject.repositories;

import finalProject.model.Parameter;
import finalProject.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByUser_IdOrderByLocalDateTimeDesc(Long user_id);

    List<Review> findAllByItem_IdOrderByLocalDateTimeDesc(Long item_id);
}
