package finalProject.services;

import finalProject.model.Category;
import finalProject.model.CategoryOfParameter;
import finalProject.model.Parameter;
import finalProject.model.SecondCategory;
import finalProject.repositories.CategoryOfParameterRepository;
import finalProject.repositories.CategoryRepository;
import finalProject.repositories.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryOfParameterService {
    @Autowired
    CategoryOfParameterRepository categoryOfParameterRepository;
    @Autowired
    SecondCategoryService secondCategoryService;
    @Autowired
    ParameterRepository parameterRepository;


    public List<CategoryOfParameter> getAllCategoriesOfParameter() {
        return categoryOfParameterRepository.findAll();
    }

    public List<CategoryOfParameter> getAllCategoriesOfParameterBySecondCategoryId(Long id) {
        return secondCategoryService.getSecondCategoryById(id).getCategoryOfParameters();
    }

    public CategoryOfParameter getCategoryOfParameter(Long categoryOfParameter_id) {
        return categoryOfParameterRepository.findById(categoryOfParameter_id).orElse(null);
    }

    public CategoryOfParameter addCategoryOfParameter(CategoryOfParameter categoryOfParameter) {
        return categoryOfParameterRepository.save(categoryOfParameter);
    }

    public boolean deleteCategoryOfParameter(CategoryOfParameter categoryOfParameter) {
        boolean flag = true;
        categoryOfParameter = categoryOfParameterRepository.findById(categoryOfParameter.getId()).orElse(null);
        try {
            List<SecondCategory> secondCategories = secondCategoryService.getSecondCategoriesByCategoryOfParameter(categoryOfParameter.getId());
            for (SecondCategory secondCategory : secondCategories){
                List<CategoryOfParameter> categoriesOfParameters = secondCategory.getCategoryOfParameters();
                categoriesOfParameters.remove(categoryOfParameter);
                secondCategory.setCategoryOfParameters(categoriesOfParameters);
            }
            List<Parameter> parameters = parameterRepository.findAllByCategoryOfParameter_Id(categoryOfParameter.getId());
            for (Parameter parameter : parameters){
                parameter.setCategoryOfParameter(null);
            }
            categoryOfParameterRepository.delete(categoryOfParameter);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

}
