package com.group.todo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.group.todo.DTO.UserResponseDTO;
import com.group.todo.entites.User;
import com.group.todo.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/users")
    public List<UserResponseDTO> getAllUsers() {
        List<UserResponseDTO> list = new ArrayList<>();
        for (User user : userService.getAllUsers()) {
            list.add(new UserResponseDTO(user.getId(), user.getUsername()));
        }
        return list;
    }
    

}
