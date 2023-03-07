package finalProject.controllers.restcontrollers;

import finalProject.model.Brand;
import finalProject.model.Parameter;
import finalProject.services.BrandService;
import finalProject.services.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/brand")
public class BrandController {
    @Autowired
    BrandService brandService;

    @PostMapping
    public Brand addBrand(
            @RequestBody Brand brand) {
        return brandService.addBrand(brand);
    }

    @GetMapping
    public List<Brand> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping(value = "/{id}")
    public Brand getBrand(@PathVariable (name = "id") Long brand_id) {
        return brandService.getBrandById(brand_id);
    }

    @DeleteMapping
    public boolean deleteBrand(@RequestBody Brand brand) {
        return brandService.deleteBrand(brand);
    }

    @PutMapping
    public Brand updateBrand(@RequestBody Brand brand) {
        return brandService.addBrand(brand);
    }
}
