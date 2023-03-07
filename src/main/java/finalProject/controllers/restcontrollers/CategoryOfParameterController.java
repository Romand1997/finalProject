package finalProject.controllers.restcontrollers;

import finalProject.model.Category;
import finalProject.model.CategoryOfParameter;
import finalProject.model.SecondCategory;
import finalProject.services.CategoryOfParameterService;
import finalProject.services.CategoryService;
import finalProject.services.SecondCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categoryOfParameter")
public class CategoryOfParameterController {
    @Autowired
    CategoryOfParameterService categoryOfParameterService;


    @GetMapping
    public List<CategoryOfParameter> getAllCategoriesOfParameter() {
        return categoryOfParameterService.getAllCategoriesOfParameter();
    }

    @GetMapping(value = "/{id}")
    public CategoryOfParameter getCategoryOfParameter(@PathVariable (name = "id") Long categoryOfParameter_id) {
        return categoryOfParameterService.getCategoryOfParameter(categoryOfParameter_id);
    }

    @GetMapping(value = "/bySecondCategory/{id}")
    public List<CategoryOfParameter> getAllCategoriesOfParameter(@PathVariable (name = "id") Long id) {
        return categoryOfParameterService.getAllCategoriesOfParameterBySecondCategoryId(id);
    }
    @PostMapping
    public CategoryOfParameter addCategoryOfParameter(@RequestBody CategoryOfParameter categoryOfParameter) {
        return categoryOfParameterService.addCategoryOfParameter(categoryOfParameter);
    }

    @DeleteMapping
    public boolean deleteCategoryOfParameter(@RequestBody CategoryOfParameter categoryOfParameter) {
        return categoryOfParameterService.deleteCategoryOfParameter(categoryOfParameter);
    }
}
