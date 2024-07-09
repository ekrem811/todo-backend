package com.group.todo.repository;

import org.springframework.data.repository.CrudRepository;

import com.group.todo.entites.ToDoItem;

public interface ToDoItemRepo extends CrudRepository<ToDoItem, Integer> {
}