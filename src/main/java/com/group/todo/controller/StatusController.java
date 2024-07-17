package com.group.todo.controller;

import java.util.Collection;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.group.todo.DTO.StatusRequestDTO;
import com.group.todo.DTO.StatusResponseDTO;
import com.group.todo.entites.Status;
import com.group.todo.service.StatusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/statuses")
    public ResponseEntity<Iterable<StatusResponseDTO>> getAllStatutes() {
        
        Collection<StatusResponseDTO> list = new LinkedList<>();
        for (Status status : statusService.getAllStatuses())
            list.add(new StatusResponseDTO(status.getId(), status.getName(), status.getCreatedBy().getId()));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @GetMapping("/status/{id}")
    public ResponseEntity<StatusResponseDTO> getTaskId(@PathVariable Integer id) {

        try {
            Status status = statusService.getStatusById(id);
            StatusResponseDTO responseDTO = new StatusResponseDTO();
            responseDTO.setId(status.getId());
            responseDTO.setName(status.getName());
            responseDTO.setCreatorId(status.getCreatedBy().getId());
            return new ResponseEntity<StatusResponseDTO>(responseDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/status")
    public ResponseEntity<?> postNewTask(@RequestBody StatusRequestDTO statusRequestDTO) {
        Status status = new Status();
        try {
            status.setName(statusRequestDTO.getName());

            Status newStatus = statusService.postNewStatus(status);
            StatusResponseDTO response = new StatusResponseDTO();
            response.setCreatorId(newStatus.getCreatedBy().getId());
            response.setName(newStatus.getName());
            response.setId(status.getId());
            return new ResponseEntity<StatusResponseDTO>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<?> putStatusName(@PathVariable Integer id, @RequestBody StatusRequestDTO statusRequestDTO) {
        Status status = new Status();
        try {
            status.setName(statusRequestDTO.getName());
            
            Status newStatus = statusService.putStatusName(id,status);

            StatusResponseDTO response = new StatusResponseDTO();
            response.setCreatorId(newStatus.getCreatedBy().getId());
            response.setName(newStatus.getName());
            response.setId(status.getId());
            return new ResponseEntity<StatusResponseDTO>(response, HttpStatus.OK);
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
