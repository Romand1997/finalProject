package finalProject.repositories;

import finalProject.model.CategoryOfParameter;
import finalProject.model.Parameter;
import finalProject.model.SecondCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ParameterRepository extends JpaRepository<Parameter, Long> {
    List<Parameter> findAllByCategoryOfParameter_Id(Long categoryOfParameter_id);

}
