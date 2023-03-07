package finalProject.controllers.restcontrollers;

import finalProject.model.Item;
import finalProject.model.Parameter;
import finalProject.services.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/parameter")
public class ParameterController {
    @Autowired
    ParameterService parameterService;

    @GetMapping
    public List<List<Parameter>> getAllParametersGroupedByCategoryOfParameter() {
        return parameterService.getAllParametersGroupedByCategoryOfParameter();
    }

    @GetMapping(value = "/parameters")
    public List<Parameter> getAllParameters() {
        return parameterService.getAllParameters();
    }

    @GetMapping(value = "/parameter_id/{id}")
    public Parameter getParameter(@PathVariable (name = "id") Long categoryOfParameter_id) {
        return parameterService.getParameterById(categoryOfParameter_id);
    }

    @GetMapping(value = "/parameters/categoryOfParameter/{id}")
    public List<Parameter> getAllParametersByCategoryOfParameter(@PathVariable (name = "id") Long categoryOfParameter_id) {
        return parameterService.getAllParametersByCategoryOfParameter(categoryOfParameter_id);
    }

    @GetMapping(value = "/{id}")
    public List<List<Parameter>> getParameterBySecondCategoryId(
            @PathVariable(name = "id") Long id) {
        return parameterService.getParametersBySecondCategory(id);
    }

    @PostMapping
    public Parameter addParameter(
            @RequestBody Parameter parameter) {
        return parameterService.addParameter(parameter);
    }

    @PutMapping
    public Parameter updateParameter(
            @RequestBody Parameter parameter) {
        return parameterService.addParameter(parameter);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Boolean deleteParameter(@RequestBody Parameter parameter) {
        return parameterService.deleteParameter(parameter);
    }
}
