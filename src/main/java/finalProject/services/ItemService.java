package finalProject.services;

import finalProject.model.Item;
import finalProject.model.Parameter;
import finalProject.repositories.BrandRepository;
import finalProject.repositories.ItemRepository;
import finalProject.repositories.SecondCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    UserService userService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    BrandService brandService;
    @Autowired
    ParameterService parameterService;
    @Autowired
    SecondCategoryService secondCategoryService;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public List<Item> getAllItemsBySecondCategoryOrBrand(Long secondCategory_id, Long brand_id) {
        if (secondCategory_id == 0L || brand_id == 0L) {
            if (secondCategory_id == 0L) {
                return itemRepository.findAllByBrandIdOrderBySecondCategoryAsc(brand_id);
            } else {
                return itemRepository.findAllBySecondCategoryIdOrderBySecondCategoryAsc(secondCategory_id);
            }
        } else {
            return itemRepository.findAllBySecondCategoryIdAndBrandIdOrderBySecondCategoryAsc(secondCategory_id, brand_id);

        }


    }

    public List<Item> getAllItemsBySecondCategoryId(Long id) {
        return itemRepository.findAllBySecondCategoryIdOrderBySecondCategoryAsc(id);
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public Item addItem(Item item) {
        Item newItem = item;
//        item.setUser(userService.getUser());
        newItem.setAddingDateTime(LocalDateTime.now());
        return itemRepository.save(newItem);
    }

    public Boolean deleteItem(Item item) {
        Boolean flag = true;
        try {
            itemRepository.delete(item);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}
