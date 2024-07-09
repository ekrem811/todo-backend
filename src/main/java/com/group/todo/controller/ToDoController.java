package com.group.todo.controller;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.group.todo.entites.ToDoItem;
import com.group.todo.service.ToDoItemService;

import org.springframework.web.bind.annotation.PutMapping;



@RestController
public class ToDoController {

    private final ToDoItemService toDoItemService;

    public ToDoController(ToDoItemService toDoItemService) {
        this.toDoItemService = toDoItemService;
    }

    @GetMapping("/items")
    ResponseEntity<Iterable<ToDoItem>> getAllToDoItems() {
        return new ResponseEntity<Iterable<ToDoItem>>(toDoItemService.getAllToDoItems(), HttpStatus.OK);
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<ToDoItem> getToDoById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<ToDoItem>( toDoItemService.getToDoById(id), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

    @PostMapping("/item")
    public ResponseEntity<ToDoItem>  postNewToDoItem(@RequestBody ToDoItem toDoItem) {
        try {
            
            return new ResponseEntity<ToDoItem>(toDoItemService.postNewToDoItem(toDoItem), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/item/switch/{id}")
    public ResponseEntity<ToDoItem> putToDoItemDone(@PathVariable Integer id) {
        try {
            return new ResponseEntity<ToDoItem>(toDoItemService.putToDoItemDone(id), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/item/name/{id}")
    public ResponseEntity<ToDoItem> putToDoItemName(@PathVariable Integer id, @RequestBody ToDoItem toDoItem) {
        try {
            return new ResponseEntity<ToDoItem>(toDoItemService.putToDoItemName(id, toDoItem), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/item/{id}")
    void deleteToDoItem(@PathVariable Integer id) {
        try {
            toDoItemService.deleteToDoItem(id);
        } catch (IllegalArgumentException e) {
        } catch (NoSuchElementException e) {
        }
    }
    
}
