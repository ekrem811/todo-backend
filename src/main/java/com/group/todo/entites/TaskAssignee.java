package com.group.todo.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "task_assignee")
public class TaskAssignee {

    @Id
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
}
