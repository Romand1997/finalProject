package finalProject.repositories;

import finalProject.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllBySecondCategoryIdOrderBySecondCategoryAsc(Long secondCategory_id);

    List<Item> findAllByBrandIdOrderBySecondCategoryAsc(Long brand_id);

    List<Item> findAllBySecondCategoryIdAndBrandIdOrderBySecondCategoryAsc(Long secondCategory_id, Long brand_id);

    List<Item> findAllByParametersIdOrderBySecondCategoryAsc(Long parameter_id);
}
