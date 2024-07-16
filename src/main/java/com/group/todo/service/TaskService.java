package com.group.todo.service;

import java.util.NoSuchElementException;

import javax.naming.AuthenticationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.group.todo.entites.Task;
import com.group.todo.entites.User;
import com.group.todo.repository.TaskRepo;
import com.group.todo.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    public Iterable<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public Task getTaskById(Integer id) throws NoSuchElementException, IllegalArgumentException {
        return taskRepo.findById(id).get();
    }

    public Task postNewTask(Task task) throws IllegalArgumentException, AuthenticationException {
        try {
            User user = getCurrentUser();
            task.setCreatedBy(user);
            return taskRepo.save(task);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException();
        } catch (Exception e) {
            throw new AuthenticationException();
        }
    }

    private User getCurrentUser() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        throw new Exception("Authenticated user can't be found");

    }

    public Task putTaskName(Integer id, Task toDoItem) throws IllegalArgumentException, NoSuchElementException {
        Task found = taskRepo.findById(id).get();
        found.setName(toDoItem.getName());
        taskRepo.save(found);
        return found;
    }

    public void deleteTask(Integer id) throws IllegalArgumentException {
        taskRepo.deleteById(id);
    }
}
