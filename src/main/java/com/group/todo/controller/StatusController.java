package com.group.todo.controller;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.group.todo.entites.Status;
import com.group.todo.service.StatusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@Controller
@CrossOrigin(origins = "http://localhost:5173")
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/statuses")
    public ResponseEntity<Iterable<Status>> getAllStatutes() {
        return new ResponseEntity<>(this.statusService.getAllStatuses(), HttpStatus.OK);
    }
    
    @GetMapping("/status/{id}")
    public ResponseEntity<Status> getTaskId(@PathVariable Integer id) {
        try {
            return new ResponseEntity<Status>( statusService.getStatusById(id), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/status")
    public ResponseEntity<?> postNewTask(@RequestBody Status status) {
        try {
            return new ResponseEntity<Status>(statusService.postNewStatus(status), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<?> putStatusName(@PathVariable Integer id, @RequestBody Status status) {
        try {
            return new ResponseEntity<Status>(statusService.putStatusName(id, status), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/status/{id}")
    public ResponseEntity<?> deleteStatus(@PathVariable Integer id) {
        try {
            statusService.deleteStatus(id);
            return new ResponseEntity<>("Status with id " + id + "deleted.", HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } 
    }
}
