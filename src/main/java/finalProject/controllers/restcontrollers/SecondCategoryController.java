package finalProject.controllers.restcontrollers;

import finalProject.model.Category;
import finalProject.model.SecondCategory;
import finalProject.services.CategoryService;
import finalProject.services.SecondCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/secondCategory")
public class SecondCategoryController {
    @Autowired
    SecondCategoryService secondCategoryService;

    @GetMapping
    public List<SecondCategory> getAllSecondCategory() {
        return secondCategoryService.getAllSecondCategory();
    }

    @GetMapping(value = "/{id}")
    public SecondCategory getSecondCategoryById(@PathVariable(name = "id") Long secondCategory_id) {
        return secondCategoryService.getSecondCategoryById(secondCategory_id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public SecondCategory addSecondCategory(@RequestBody SecondCategory secondCategory) {
        return secondCategoryService.addSecondCategory(secondCategory);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public SecondCategory updateSecondCategory(@RequestBody SecondCategory secondCategory) {
        return secondCategoryService.addSecondCategory(secondCategory);
    }

    @PutMapping(value = "/addingCategoriesOfParameter")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<SecondCategory> addingCategoriesOfParameterToSecondCategories(@RequestBody List<SecondCategory> secondCategories) {
        return secondCategoryService.updateSecondCategories(secondCategories);
    }

    @PutMapping(value = "/{id}/updatingCategoriesOfParameter")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<SecondCategory> updatingCategoriesOfParameterInSecondCategories(@RequestBody List<SecondCategory> secondCategories,
                                                                                @PathVariable(name = "id") Long categoryOfParameter_id) {
        return secondCategoryService.updatingCategoriesOfParameterInSecondCategories(secondCategories, categoryOfParameter_id);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public boolean deleteSecondCategory(@RequestBody SecondCategory secondCategory) {
        return secondCategoryService.deleteSecondCategory(secondCategory);
    }
}
