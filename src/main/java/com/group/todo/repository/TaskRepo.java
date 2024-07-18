package com.group.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.group.todo.entites.Task;

public interface TaskRepo extends CrudRepository<Task, Integer> {

}