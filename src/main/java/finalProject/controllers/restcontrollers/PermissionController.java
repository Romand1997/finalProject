package finalProject.controllers.restcontrollers;

import finalProject.model.Permission;
import finalProject.model.User;
import finalProject.services.PermissionService;
import finalProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/permission")
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @GetMapping
    public List<Permission> getAllUsers() {
        List<Permission> permissions = permissionService.getAllPermissions();
        return permissions;
    }
}
