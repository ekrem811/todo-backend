package com.group.todo.service;

import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.group.todo.entites.User;
import com.group.todo.repository.UserRepo;

@Service
public class UserService {


    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public User getUserById(Integer id) throws NoSuchElementException, IllegalArgumentException {
        return userRepo.findById(id).get();
    }

    public User postNewTask(User user) throws IllegalArgumentException {
        try {
            return userRepo.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException();
        }
    }
}
