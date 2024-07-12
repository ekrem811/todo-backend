package com.group.todo.service;

import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.group.todo.entites.Task;
import com.group.todo.repository.TaskRepo;

@Service
public class TaskService {

    private final TaskRepo taskRepo;

    public TaskService(TaskRepo toDoItemRepo) {
        this.taskRepo = toDoItemRepo;
    }

    public Iterable<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public Task getTaskById(Integer id) throws NoSuchElementException, IllegalArgumentException {
        return taskRepo.findById(id).get();
    }

    public Task postNewTask(Task task) throws IllegalArgumentException {
        try {
            return taskRepo.save(task);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException();
        }
    }

    public Task putTaskName(Integer id, Task toDoItem) throws IllegalArgumentException, NoSuchElementException {
        Task found = taskRepo.findById(id).get();
        found.setName(toDoItem.getName());
        taskRepo.save(found);
        return found;
    }

    public void deleteTask(Integer id) throws IllegalArgumentException{
        taskRepo.deleteById(id);
    }
}
