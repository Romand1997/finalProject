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
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    SecondCategoryService secondCategoryService;

    @GetMapping
    public List<Category> catalog() {
        return categoryService.getAllCategory();
    }

    @GetMapping(value = "/{id}")
    public List<SecondCategory> catalogNext(@PathVariable(name = "id") Long id) {
        return secondCategoryService.getSecondCategoryByCategoryId(id);
    }

    @GetMapping(value = "/category/{id}")
    public Category getCategory(@PathVariable(name = "id") Long id) {
        return categoryService.getCategory(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Category addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Category updateCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Boolean deleteCategory(@RequestBody Category category) {
        return categoryService.deleteCategory(category);
    }
}
