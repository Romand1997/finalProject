package finalProject.repositories;

import finalProject.model.Category;
import finalProject.model.CategoryOfParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CategoryOfParameterRepository extends JpaRepository<CategoryOfParameter, Long> {

    CategoryOfParameter findByNameEqualsIgnoreCase(String name);
}
