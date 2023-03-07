package finalProject.services;

import finalProject.model.CategoryOfParameter;
import finalProject.model.SecondCategory;
import finalProject.repositories.CategoryOfParameterRepository;
import finalProject.repositories.SecondCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecondCategoryService {

    @Autowired
    CategoryOfParameterRepository categoryOfParameterRepository;
    @Autowired
    SecondCategoryRepository secondCategoryRepository;

    public List<SecondCategory> getAllSecondCategory() {
        return secondCategoryRepository.findAll();
    }

    public List<SecondCategory> getSecondCategoryByCategoryId(Long id) {
        return secondCategoryRepository.findAllByCategory_IdOrderByNameAsc(id);
    }

    public SecondCategory getSecondCategoryById(Long id) {
        return secondCategoryRepository.findById(id).orElse(null);
    }

    public List<SecondCategory> getSecondCategoriesByCategoryOfParameter(Long categoryOfParameter_id) {
        return secondCategoryRepository.findAllByCategoryOfParameters_IdOrderByNameAsc(categoryOfParameter_id);
    }

    public SecondCategory addSecondCategory(SecondCategory secondCategory) {
        return secondCategoryRepository.save(secondCategory);
    }

    public boolean deleteCategoryFromSecondCategory(SecondCategory secondCategory) {
        boolean flag = true;
        try {
            secondCategory.setCategory(null);
            secondCategoryRepository.save(secondCategory);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public boolean deleteSecondCategory(SecondCategory secondCategory) {
        boolean flag = true;
        try {
            secondCategoryRepository.delete(addSecondCategory(secondCategory));
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public List<SecondCategory> updateSecondCategories(List<SecondCategory> secondCategories) {
        List<SecondCategory> newSecondCategories = new ArrayList<>();
        for (SecondCategory secondCategory : secondCategories) {
            SecondCategory newSecondCategory = secondCategoryRepository.findById(secondCategory.getId()).orElse(null);
            if (newSecondCategory != null) {
                List<CategoryOfParameter> newCategoryOfParameter = new ArrayList<>();
                if (newSecondCategory.getCategoryOfParameters() != null) {
                    newCategoryOfParameter = newSecondCategory.getCategoryOfParameters();
                }
                newCategoryOfParameter.addAll(secondCategory.getCategoryOfParameters());
                newSecondCategory.setCategoryOfParameters(newCategoryOfParameter);
                newSecondCategories.add(secondCategoryRepository.save(newSecondCategory));
            }
        }
        return newSecondCategories;
    }

    public List<SecondCategory> updatingCategoriesOfParameterInSecondCategories(List<SecondCategory> secondCategories, Long categoryOfParameter_id) {
        List<SecondCategory> newSecondCategories = new ArrayList<>();
        CategoryOfParameter categoryOfParameter = categoryOfParameterRepository.findById(categoryOfParameter_id).orElse(null);
        if (categoryOfParameter!=null) {
            if (secondCategories.size()==0) {
                secondCategories = secondCategoryRepository.findAll();
                for (SecondCategory newSecondCategory :secondCategories){
                    boolean flag = false;
                    List<CategoryOfParameter> newCategoriesOfParameter = newSecondCategory.getCategoryOfParameters();
                    for (CategoryOfParameter newCategoryOfParameter: newCategoriesOfParameter){
                        if(newCategoryOfParameter.equals(categoryOfParameter)){
                            System.out.println("зашел в if");
                            flag = true;
                        }
                    }
                    if (flag) {
                        newCategoriesOfParameter.remove(categoryOfParameter);
                    }
                    newSecondCategories.add(newSecondCategory);
                }
            } else {
                for (SecondCategory secondCategory : secondCategories) {
                    boolean flag = false;
                    SecondCategory newSecondCategory = getSecondCategoryById(secondCategory.getId());
                    if (newSecondCategory.getCategoryOfParameters() == null) {
                        System.out.println("нул");
                    }
                    List<CategoryOfParameter> newCategoriesOfParameter = newSecondCategory.getCategoryOfParameters();
                    for (CategoryOfParameter newCategoryOfParameter : newSecondCategory.getCategoryOfParameters()) {
                        if (newCategoryOfParameter.equals(categoryOfParameter)) {
                            System.out.println("зашел в иф");
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        newCategoriesOfParameter.add(categoryOfParameter);
                        newSecondCategory.setCategoryOfParameters(newCategoriesOfParameter);
                        newSecondCategories.add(newSecondCategory);
                    }
                }
            }
            System.out.println(newSecondCategories);
        }
        secondCategoryRepository.saveAll(newSecondCategories);
        return newSecondCategories;
    }
}
