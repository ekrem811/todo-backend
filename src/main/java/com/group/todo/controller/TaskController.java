package com.group.todo.controller;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.group.todo.DTO.TaskRequestDTO;
import com.group.todo.DTO.TaskResponseDTO;
import com.group.todo.entites.Task;
import com.group.todo.service.TaskService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    ResponseEntity<Collection<TaskResponseDTO>> getAllTasks() {
        Collection<TaskResponseDTO> list = new LinkedList<>();
        for (Task task : taskService.getAllTasks())
            list.add(new TaskResponseDTO(task.getId(), task.getName(), task.getCreatedBy().getUsername()));

        return new ResponseEntity<Collection<TaskResponseDTO>>(list, HttpStatus.OK);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskId(@PathVariable Integer id) {
        try {
            Task found = taskService.getTaskById(id);
            return new ResponseEntity<TaskResponseDTO>(new TaskResponseDTO(found.getId(), found.getName(), found.getCreatedBy().getUsername()), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

    @PostMapping("/task")
    public ResponseEntity<?> postNewTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        Task task = new Task();
        try {
            task.setName(taskRequestDTO.getName());
            Task newTask = taskService.postNewTask(task);
            TaskResponseDTO response = new TaskResponseDTO(newTask.getId(), newTask.getName(), newTask.getCreatedBy().getUsername());
            return new ResponseEntity<TaskResponseDTO>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/task/name/{id}")
    public ResponseEntity<TaskResponseDTO> putTaskName(@PathVariable Integer id, @RequestBody TaskRequestDTO requestDTO) {
        Task task = new Task();
        try {
            task.setName(requestDTO.getName());
            task = taskService.putTaskName(id, task);
            return new ResponseEntity<TaskResponseDTO>(new TaskResponseDTO(task.getId() ,task.getName(), task.getCreatedBy().getUsername()), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Integer id) {
        try {
            taskService.deleteTask(id);
            return new ResponseEntity<>("Status with id " + id + "deleted.", HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } 
    }
    
}
