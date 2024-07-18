package com.group.todo.DTO;

import com.group.todo.entites.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskResponseDTO {

    private Integer id;
    private String name;
    private TaskUser reporter;
    private TaskUser assignee;
    private StatusResponseDTO status;

    @AllArgsConstructor
    @Data
    private class TaskUser {
        private String username;
        private Integer id;
    }

    public TaskResponseDTO(Task task) {
        this.setName(task.getName());
        this.setId(task.getId());
        this.setName(task.getName());
        if (task.getAssignee() != null)
            this.setAssignee(new TaskUser(task.getAssignee().getUsername(), task.getAssignee().getId()));
        this.setReporter(new TaskUser(task.getCreatedBy().getUsername(), task.getCreatedBy().getId()));
        if (task.getStatus() != null)
        this.setStatus(new StatusResponseDTO(task.getStatus().getId(), task.getStatus().getName()));
        
    }

}
