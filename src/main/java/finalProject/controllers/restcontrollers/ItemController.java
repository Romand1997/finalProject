package finalProject.controllers.restcontrollers;

import finalProject.model.Item;
import finalProject.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/item")
public class ItemController {
    @Autowired
    ItemService itemService;

    @GetMapping
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }
    @GetMapping(value = "/secondCategory/{secondCategory_id}/brand/{brand_id}")
    public List<Item> getAllItemsBySecondCategoryOrBrand(@PathVariable (name = "secondCategory_id") Long secondCategory_id,
                                                         @PathVariable (name = "brand_id") Long brand_id){
        return itemService.getAllItemsBySecondCategoryOrBrand(secondCategory_id, brand_id);
    }
    @GetMapping(value = "/items/{id}")
    public List<Item> getAllItemsBySecondCategory(@PathVariable(name = "id") Long id) {
        return itemService.getAllItemsBySecondCategoryId(id);
    }

    @GetMapping(value = "/{id}")
    public Item getItemById(@PathVariable(name = "id") Long id) {
        return itemService.getItemById(id);
    }

    //    @PutMapping (value = "/items/{id}")
//    public List<Item> addItemToCart(@PathVariable (name = "id") Long id){
//        return itemService.getAllItemsBySecondCategoryId(id);
//    }
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Item addItem(@RequestBody Item item) {
        return itemService.addItem(item);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Boolean deleteItem(@RequestBody Item item) {
        return itemService.deleteItem(item);
    }
}
