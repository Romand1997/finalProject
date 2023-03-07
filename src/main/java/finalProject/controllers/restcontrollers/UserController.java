package finalProject.controllers.restcontrollers;

import finalProject.model.User;
import finalProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        List<User>users = userService.getAllUser();
        return users;
    }

    @PutMapping
    public User updatePermissionFromUser(@RequestBody User user) {
        return userService.updatePermissionFromUser(user);
    }

}
