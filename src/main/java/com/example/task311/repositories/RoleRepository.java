package com.example.task311.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.task311.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}