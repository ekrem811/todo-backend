package com.group.todo.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.group.todo.entites.ToDoItem;
import com.group.todo.repository.ToDoItemRepo;

@Service
public class ToDoItemService {

    public ToDoItemService(ToDoItemRepo toDoItemRepo) {
        this.toDoItemRepo = toDoItemRepo;
    }

    private final ToDoItemRepo toDoItemRepo;

    public Iterable<ToDoItem> getAllToDoItems() {
        return toDoItemRepo.findAll();
    }

    public ToDoItem getToDoById(Integer id) throws NoSuchElementException, IllegalArgumentException {
        return toDoItemRepo.findById(id).get();
    }

    public ToDoItem postNewToDoItem(ToDoItem toDoItem) throws IllegalArgumentException {
        return toDoItemRepo.save(toDoItem);
    }

    public ToDoItem putToDoItemDone(Integer id) throws IllegalArgumentException, NoSuchElementException {
        ToDoItem found = toDoItemRepo.findById(id).get();
        found.setDone(!found.isDone());
        toDoItemRepo.save(found);
        return found;
    }

    public ToDoItem putToDoItemName(Integer id, ToDoItem toDoItem) throws IllegalArgumentException, NoSuchElementException {
        ToDoItem found = toDoItemRepo.findById(id).get();
        found.setName(toDoItem.getName());
        toDoItemRepo.save(found);
        return found;
    }

    public void deleteToDoItem(Integer id) throws IllegalArgumentException{
        toDoItemRepo.deleteById(id);
    }
}
