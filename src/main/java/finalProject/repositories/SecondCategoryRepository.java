package finalProject.repositories;

import finalProject.model.CategoryOfParameter;
import finalProject.model.SecondCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface SecondCategoryRepository extends JpaRepository<SecondCategory, Long> {

    List<SecondCategory> findAllByCategory_IdOrderByNameAsc(Long id);

    List<SecondCategory> findAllByCategoryOfParameters_IdOrderByNameAsc(Long categoryOfParameters_id);

    List<SecondCategory> deleteSecondCategoriesByCategoryOfParameters_Id(Long categoryOfParameter_id);
}
