package com.group.todo.repository;


import org.springframework.data.repository.CrudRepository;

import com.group.todo.entites.Task;

public interface TaskRepo extends CrudRepository<Task, Integer> {

    Iterable<Task> findByNameContaining(String search);
}