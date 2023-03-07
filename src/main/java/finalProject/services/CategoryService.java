package finalProject.services;

import finalProject.model.Category;
import finalProject.model.SecondCategory;
import finalProject.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SecondCategoryService secondCategoryService;

    public List<Category> getAllCategory (){
        return categoryRepository.findAll();
    }

    public Category getCategory (Long category_id){
        return categoryRepository.findById(category_id).orElse(null);
    }
    public Category addCategory (Category category){
        return categoryRepository.save(category);
    }

    public boolean deleteCategory (Category category){
        boolean flag = true;
        try {
            List<SecondCategory>secondCategories = secondCategoryService.getSecondCategoryByCategoryId(category.getId());
            for (SecondCategory secondCategory: secondCategories) {
                secondCategoryService.deleteCategoryFromSecondCategory(secondCategory);
            }
            categoryRepository.delete(category);
        } catch (Exception e){
            flag = false;
        }
        return flag;
    }
}
