package com.example.task311.controller;

import com.example.task311.model.User;
import com.example.task311.service.RoleService;
import com.example.task311.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/list_users")
    public String viewUsersList(@ModelAttribute("user") User user, Model model, Principal principal) {
        model.addAttribute("allUsers", userService.findAllUsers());
        model.addAttribute("allRoles", roleService.findAllRoles());
        model.addAttribute("currentUser", userService.findByUsername(principal.getName()));
        return "users";
    }
    @GetMapping("/new")
    public String addUserPage(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("allRoles", roleService.findAllRoles());
        return "new_user";
    }
    @PostMapping("/adduser")
    public String addUser(@ModelAttribute("user") User user, @RequestParam("role_select") Long[] roles ){
        roleService.setRolesToUser(user, roles);
        userService.addUser(user);
        return "redirect:/admin/list_users";
    }
    @PostMapping("/update/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("role_select") Long[] roles) {
        roleService.setRolesToUser(user, roles);
        userService.updateUser(user);
        return "redirect:/admin/list_users";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/list_users";
    }
    @GetMapping("/getOne")
    @ResponseBody
    public User getOne(Long id) {
        return userService.findById(id);
    }
}
