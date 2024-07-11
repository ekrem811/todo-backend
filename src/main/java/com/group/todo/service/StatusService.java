package com.group.todo.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.group.todo.entites.Status;
import com.group.todo.repository.StatusRepo;

@Service
public class StatusService {

    private final StatusRepo statusRepo;

    public StatusService(StatusRepo statusRepo) {
        this.statusRepo = statusRepo;
    }

    public Iterable<Status> getAllStatuses() {
        return statusRepo.findAll();
    }

    public Status getStatusById(Integer id) throws NoSuchElementException, IllegalArgumentException {
        return statusRepo.findById(id).get();
    }

    public Status postNewStatus(Status status) throws IllegalArgumentException {
        return statusRepo.save(status);
    }

    public Status putStatusName(Integer id, Status status) throws IllegalArgumentException, NoSuchElementException {
        Status found = statusRepo.findById(id).get();
        found.setName(status.getName());
        statusRepo.save(found);
        return found;
    }

    public void deleteStatus(Integer id) throws IllegalArgumentException{
        statusRepo.deleteById(id);
    }

}
