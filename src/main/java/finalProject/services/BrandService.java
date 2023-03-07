package finalProject.services;

import finalProject.model.Brand;
import finalProject.model.Item;
import finalProject.repositories.BrandRepository;
import finalProject.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    BrandRepository brandRepository;

    @Autowired
    ItemRepository itemRepository;
    public List<Brand> getAllBrands (){
        return brandRepository.findAll();
    }

    public Brand getBrandById (Long id){
        return brandRepository.findById(id).orElse(null);
    }

    public Brand addBrand (Brand brand){
        return brandRepository.save(brand);
    }

    public boolean deleteBrand (Brand brand){
        boolean flag = true;
        try{
            List<Item> items = itemRepository.findAllByBrandIdOrderBySecondCategoryAsc(brand.getId());
            for (Item item : items){
                item.setBrand(null);
            }
            brandRepository.delete(brand);
        } catch (Exception e){
            flag = false;
        }
        return flag;
    }
}
