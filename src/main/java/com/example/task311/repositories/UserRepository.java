package com.example.task311.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.task311.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT p FROM User p JOIN FETCH p.roles WHERE p.username = (:username)")
    User findByUsername(@Param("username")String username);

}
