package com.example.projectyweb.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.projectyweb.models.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query( value = "select u from User u")
    List<User> findByEmail(String email);
    //List<User> findByPosition(String position);
}
