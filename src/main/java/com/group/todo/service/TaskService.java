package com.group.todo.service;

import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.Forbidden;

import com.group.todo.DTO.TaskRequestDTO;
import com.group.todo.entites.Status;
import com.group.todo.entites.Task;
import com.group.todo.entites.User;
import com.group.todo.repository.StatusRepo;
import com.group.todo.repository.TaskRepo;
import com.group.todo.repository.UserRepo;
import com.group.todo.util.AuthUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepo taskRepo;
    private final StatusRepo statusRepo;
    private final AuthUtil authUtil;
    private final UserRepo userRepo;

    public Iterable<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public Task getTaskById(Integer id) throws NoSuchElementException, IllegalArgumentException {
        return taskRepo.findById(id).get();
    }

    public Task postNewTask(TaskRequestDTO requestDTO) throws IllegalArgumentException {
        Task task = new Task();
        try {
            task.setName(requestDTO.getName());
            User user = authUtil.getCurrentUser();
            task.setCreatedBy(user);
            if (requestDTO.getStatusId() != null) {
                Status status = statusRepo.findById(requestDTO.getStatusId()).get();
                if (status != null)
                    task.setStatus(status);
            }

            if (requestDTO.getAssigneeId() != null) {
                User assignee = userRepo.findById(requestDTO.getAssigneeId()).get();
                if (assignee != null)
                    task.setAssignee(assignee);
            }
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

    public void deleteTask(Integer id) throws IllegalArgumentException, Forbidden {
        User current = authUtil.getCurrentUser();
        Task found = taskRepo.findById(id).get();
        if (current.getId() != found.getCreatedBy().getId())
            throw Forbidden.create(HttpStatus.FORBIDDEN, "User is not allowed the delete this taks.", null, null, null);

        taskRepo.deleteById(id);
    }

    public Task putTaskStatus(Integer id, TaskRequestDTO requestDTO) throws Forbidden {
        User current = authUtil.getCurrentUser();
        Task found = taskRepo.findById(id).get();
        if (current.getId() != found.getCreatedBy().getId())
            throw Forbidden.create(HttpStatus.FORBIDDEN, "User is not allowed the change this taks.", null, null, null);
        Status status = statusRepo.findById(requestDTO.getStatusId()).get();
        found.setStatus(status);
        taskRepo.save(found);
        return found;
    }

    public Task putTaskAssignee(Integer id, TaskRequestDTO requestDTO) {
        Task found = taskRepo.findById(id).get();
        User current = authUtil.getCurrentUser();
        if (current.getId() != found.getCreatedBy().getId())
            throw Forbidden.create(HttpStatus.FORBIDDEN, "User is not allowed the change this taks.", null, null, null);
        User assignee = userRepo.findById(requestDTO.getAssigneeId()).get();
        found.setAssignee(assignee);
        taskRepo.save(found);
        return found;
    }

    public Task putTask(Integer id, TaskRequestDTO requestDTO) throws Forbidden {
        Task found = taskRepo.findById(id).get();
        User current = authUtil.getCurrentUser();
        if (current.getId() != found.getCreatedBy().getId()) {
            if (found.getAssignee() != null && current.getId() == found.getAssignee().getId()) {
                if (requestDTO.getStatusId() == null) {
                    found.setStatus(null);
                } else {

                    Status status;
                    try {
                        status = statusRepo.findById(requestDTO.getStatusId()).get();
                        found.setStatus(status);
                    } catch (NoSuchElementException e) {
                        throw new IllegalArgumentException();
                    }
                }
            } else {
                throw Forbidden.create(HttpStatus.FORBIDDEN, "User is not allowed the change this taks.", null, null,
                        null);
            }
        } else {
            System.out.println(requestDTO.getName());
            found.setName(requestDTO.getName());
            if (requestDTO.getStatusId() == null) {
                found.setStatus(null);
            } else {

                Status status;
                try {
                    status = statusRepo.findById(requestDTO.getStatusId()).get();
                    found.setStatus(status);
                } catch (NoSuchElementException e) {
                    throw new IllegalArgumentException();
                }
            }

            if (requestDTO.getAssigneeId() == null) {
                found.setAssignee(null);
            } else {

                User assignee;
                try {
                    assignee = userRepo.findById(requestDTO.getAssigneeId()).get();
                    found.setAssignee(assignee);
                } catch (NoSuchElementException e) {
                    throw new IllegalArgumentException();
                }
            }
        }

        return taskRepo.save(found);
    }
}
