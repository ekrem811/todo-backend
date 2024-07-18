package com.group.todo.entites;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;



@Data
@Entity
public class Task extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    private User assignee;

    @ManyToOne
    private Status status;

    public Task(String name, User createdBy) {
        super(createdBy);
        this.name = name;
    }

    public Task() {
        super();
    }

}
