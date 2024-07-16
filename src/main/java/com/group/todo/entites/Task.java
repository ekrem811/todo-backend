package com.group.todo.entites;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Task extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany
    @JoinTable(name = "task_assignee")
    private List<User> assignee;

    public List<User> getAssignee() {
        return assignee;
    }

    public void setAssignee(List<User> assignees) {
        this.assignee = assignees;
    }

    public Task(String name, User createdBy, List<User> assignees) {
        super(createdBy);
        this.name = name;
        this.assignee = assignees;
    }

    public Task() {
        super();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
