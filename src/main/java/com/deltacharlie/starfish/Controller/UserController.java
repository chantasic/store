package com.deltacharlie.starfish.Controller;

import com.deltacharlie.starfish.Entity.User;
import com.deltacharlie.starfish.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/users/new")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "create-user";
    }

    @PostMapping("/users")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users/find")
    public String findUserByName(@RequestParam("name") String name, Model model) {
        List<User> users = userService.findByName(name);
        model.addAttribute("users", users);
        return "user-list"; // Reusing the user-list template to display the search results
    }

    @GetMapping("/users/update")
    public String updateUserForm(@RequestParam("id") Long id, Model model) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
            return "update-user";
        } else {
            return "redirect:/users";
        }
    }

    @PostMapping("/users/update")
    public String updateUser(@ModelAttribute("user") User updatedUser) {
        userService.save(updatedUser);
        return "redirect:/users";
    }

    @PostMapping("/users/delete")
    public String deleteUserById(@RequestParam("id") Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}

