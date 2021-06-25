package com.example.task311.service;

import com.example.task311.model.Role;
import com.example.task311.model.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    void addUser(User user);
    void updateUser(User user);
    void deleteById(long id);
    User findById(long id);
    List<User> findAllUsers();
    List<Role> findAllRoles();
    Role getRoleById(Long id);
    void setRolesToUser(User user, Long[] roles);
}