package com.group.todo.repository;

import org.springframework.data.repository.CrudRepository;

import com.group.todo.entites.User;

public interface UserRepo extends CrudRepository<User, Integer> {

}
