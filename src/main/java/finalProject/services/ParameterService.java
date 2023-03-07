package finalProject.services;

import finalProject.model.Category;
import finalProject.model.CategoryOfParameter;
import finalProject.model.Item;
import finalProject.model.Parameter;
import finalProject.repositories.CategoryOfParameterRepository;
import finalProject.repositories.ItemRepository;
import finalProject.repositories.ParameterRepository;
import finalProject.repositories.SecondCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParameterService {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ParameterRepository parameterRepository;

    @Autowired
    CategoryOfParameterService categoryOfParameterService;

    @Autowired
    SecondCategoryRepository secondCategoryRepository;

    public List<Parameter> getAllParameters() {
        return parameterRepository.findAll();
    }

    public List<Parameter> getAllParametersByCategoryOfParameter(Long categoryOfParameter_id) {
        return parameterRepository.findAllByCategoryOfParameter_Id(categoryOfParameter_id);
    }

    public List<List<Parameter>> getAllParametersGroupedByCategoryOfParameter() {
        List<List<Parameter>> parametersList = new ArrayList<>();
        List<CategoryOfParameter> categoriesOfParameter = categoryOfParameterService.getAllCategoriesOfParameter();
        for (CategoryOfParameter category : categoriesOfParameter) {
            parametersList.add(parameterRepository.findAllByCategoryOfParameter_Id(category.getId()));
        }
        return parametersList;
    }

    public List<List<Parameter>> getParametersBySecondCategory(Long id) {
        List<List<Parameter>> parametersList = new ArrayList<>();
        List<CategoryOfParameter> categoriesOfParameter = secondCategoryRepository.findById(id).orElse(null).getCategoryOfParameters();
        for (CategoryOfParameter category : categoriesOfParameter) {
            parametersList.add(parameterRepository.findAllByCategoryOfParameter_Id(category.getId()));
        }
        return parametersList;
    }

    public Parameter getParameterById(Long id) {
        return parameterRepository.findById(id).orElse(null);
    }

    public List<Parameter> getParameterByCategoryOfParameterId(Long categoryOfParameter_id) {
        return parameterRepository.findAllByCategoryOfParameter_Id(categoryOfParameter_id);
    }

    public Parameter addParameter(Parameter parameter) {
        return parameterRepository.save(parameter);
    }

    public Boolean deleteParameter(Parameter parameter) {
        Parameter newParameter = parameterRepository.findById(parameter.getId()).orElse(null);
        Boolean flag = true;
        try {
            List<Item> items = itemRepository.findAllByParametersIdOrderBySecondCategoryAsc(parameter.getId());
            for (Item item : items) {
                List<Parameter> parameters = item.getParameters();
                parameters.remove(newParameter);
                item.setParameters(parameters);
                itemRepository.save(item);
            }
            parameterRepository.delete(parameter);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}
