
package finalProject.controllers;

import finalProject.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller

public class FrontController {
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ItemService itemService;
    @Autowired
    OrderService orderService;

    @Autowired
    BrandService brandService;

    @GetMapping(value = "/")
    public String homePage() {
        return "home";
    }

    @GetMapping(value = "/catalog")
    public String catalogPage() {
        return "catalog";
    }

    @GetMapping(value = "/secondCatalog")
    public String catalogNextPage() {
        return "catalogSecondPage";
    }

    @GetMapping(value = "/itemsCatalog")
    public String itemsPage() {
        return "itemsPage";
    }

    @GetMapping(value = "/itemPage")
    public String itemPage(Model model) {
        model.addAttribute("amount", orderService.getAmountOfItemInOrder());
        return "ItemPage";
    }

    @GetMapping(value = "/cartPage")
    public String cartPage() {
        return "cartPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN')")
    @GetMapping(value = "/superAdminPanel")
    public String superAdminPanel() {
        return "adminPanelPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/adminPanel")
    public String adminPanel() {
        return "addItemAdminPanelPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/addItemAdminPanel")
    public String addItemAdminPanel(Model model) {
        model.addAttribute("brands", brandService.getAllBrands());
        return "addItemAdminPanelPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/addCategoryAdminPanel")
    public String addCategoryAdminPanel() {
        return "addCategoryAdminPanelPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/addSecondCategoryAdminPanel")
    public String addSecondCategoryAdminPanel() {
        return "addSecondCategoryAdminPanelPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/addParameterAdminPanel")
    public String addParameterAdminPanel() {
        return "addParameterAdminPanelPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/addCategoryOfParameterAdminPanel")
    public String addCategoryOfParameterAdminPanel() {
        return "addCategoryOfParameterAdminPanelPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/addBrandAdminPanel")
    public String addBrandAdminPanel() {
        return "addBrandAdminPanelPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/updateItemAdminPanel")
    public String updateItemAdminPanel() {
        return "updateItemAdminPanelPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/detailsOfItemAdminPanel")
    public String detailsOfItemAdminPanel() {
        return "detailsOfItemAdminPanelPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/updateCategoryAdminPanel")
    public String updateCategoryAdminPanel() {
        return "updateCategoryAdminPanelPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/detailsOfCategoryAdminPanel")
    public String detailsOfCategoryAdminPanel() {
        return "detailsOfCategoryAdminPanelPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/updateSecondCategoryAdminPanel")
    public String updateSecondCategoryAdminPanel() {
        return "updateSecondCategoryAdminPanelPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/detailsOfSecondCategoryAdminPanel")
    public String detailsOfSecondCategoryAdminPanel() {
        return "detailsOfSecondCategoryAdminPanelPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/updateParameterAdminPanel")
    public String updateParameterAdminPanel() {
        return "updateParameterAdminPanelPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/detailsOfParameterAdminPanel")
    public String detailsOfParameterAdminPanel() {
        return "detailsOfParameterAdminPanelPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/updateCategoryOfParameterAdminPanel")
    public String updateCategoryOfParameterAdminPanel() {
        return "updateCategoryOfParameterAdminPanelPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/detailsOfCategoryOfParameterAdminPanel")
    public String detailsOfCategoryOfParameterAdminPanel() {
        return "detailsOfCategoryOfParameterAdminPanelPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/updateBrandAdminPanel")
    public String updateBrandAdminPanel() {
        return "updateBrandAdminPanelPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/detailsOfBrandAdminPanel")
    public String detailsOfBrandAdminPanel() {
        return "detailsOfBrandAdminPanelPage";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/signin")
    public String signIn(Model model) {
        return "signinPage";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/profile")
    public String profilePage(Model model) {
        model.addAttribute("user", userService.getUser());
        return "profilePage";
    }

    @GetMapping(value = "/registratePage")
    public String openRegistratePage(Model model) {
        return "registratePage";
    }

    @PostMapping(value = "/addUser")
    public String addUser(@RequestParam(name = "user_email") String user_email,
                          @RequestParam(name = "full_name") String full_name,
                          @RequestParam(name = "user_password") String password,
                          @RequestParam(name = "user_rePassword") String rePassword) {
        Boolean bol = userService.addUser(user_email, full_name, password, rePassword);

        if (bol == null) {
            return "redirect:/registratePage?errorEmail";
        } else if (!bol) {
            return "redirect:/registratePage?errorPassword";
        } else {
            return "redirect:/?registrationSuccess";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/changePasswordPage")
    public String openChangePasswordPage() {
        return "changePasswordPage";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/changePassword")
    public String changePassword(@RequestParam(name = "old_password") String oldPassword,
                                 @RequestParam(name = "new_password") String newPassword,
                                 @RequestParam(name = "re_new_password") String reNewPassword) {
        String result = userService.changePassword(oldPassword, newPassword, reNewPassword);
        if (result.equals("passwordChangedSuccesfully")) {
            return "redirect:/profile?" + result;
        } else {
            return "redirect:/changePasswordPage?" + result;
        }
    }
}

