package com.group.todo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group.todo.entites.User;


public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findUserByUsername(String username);
}
