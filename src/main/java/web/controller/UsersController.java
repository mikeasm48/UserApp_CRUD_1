package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDAOImpl;
import web.model.User;
import web.service.UserService;

@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String startPage(ModelMap model) {
        model.addAttribute("users", userService.getUsers());
        return "users/index";
    }

    @GetMapping(value = "/users/list")
    public String listUsers(ModelMap model) {
        model.addAttribute("users", userService.getUsers());
        return "users/list";
    }

    @GetMapping(value = "/users/{id}")
    public String showUser(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        return "users/user";
    }

    @GetMapping("/users/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping("users")
    public String createUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/users/list";
    }

    @GetMapping("users/edit")
    public String goToEditUser(@RequestParam("id") int id, ModelMap model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "users/edit";
    }

    @PostMapping("users/edit")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("id") int id) {
        userService.updateUser(id, user);
        return "redirect:/users/list";
    }

    @PostMapping("users/delete")
    public String deleteUser(@ModelAttribute("user") User user, @RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users/list";
    }
}
