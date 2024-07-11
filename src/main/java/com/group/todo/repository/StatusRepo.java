package com.group.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.group.todo.entites.Status;

public interface StatusRepo extends CrudRepository<Status, Integer> {
}
