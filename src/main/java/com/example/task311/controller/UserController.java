package com.example.task311.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.task311.model.User;
import com.example.task311.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/admin/list_users")
    public String viewUsersList(@ModelAttribute("user") User user, Model model, Principal principal) {
        model.addAttribute("allUsers", userService.findAllUsers());
        model.addAttribute("allRoles", userService.findAllRoles());
        model.addAttribute("currentUser", userService.findByUsername(principal.getName()));
        return "users";
    }

    @GetMapping("/")
    public String login() {
        return "index";
    }

    @GetMapping("/user")
    public String showUserPage(Model model, Principal principal) {
        model.addAttribute("currentUser", userService.findByUsername(principal.getName()));
        return "user";
    }

    @GetMapping("/admin/new")
    public String addUserPage(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("allRoles", userService.findAllRoles());
        return "new_user";
    }

    @PostMapping("/adduser")
    public String addUser(@ModelAttribute("user") User user, @RequestParam("role_select") Long[] roles ){
        userService.setRolesToUser(user, roles);
        userService.addUser(user);
        return "redirect:/admin/list_users";
    }

    @GetMapping("admin/edit/{id}")
    public String showUpdateForm(@PathVariable("id")Long id, Model model, Principal principal) {
        model.addAttribute("allRoles", userService.findAllRoles());
        model.addAttribute("userRoles", userService.getRoleById(id));
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("currentUser", userService.findByUsername(principal.getName()));
        return "/user_form";
    }

    @PostMapping("/admin/update/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("role_select") Long[] roles) {
        userService.setRolesToUser(user, roles);
        userService.updateUser(user);
        return "redirect:/admin/list_users";
    }

    @GetMapping("admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/list_users";
    }

    @GetMapping("/admin/getOne")
    @ResponseBody
    public User getOne(Long id) {
        return userService.findById(id);
    }

    @PostMapping("/save")
    public String save(User user) {

        userService.updateUser(user);
        return "redirect:/admin/list_users";
    }
}
