package com.group.todo.DTO;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TaskResponseDTO extends TaskRequestDTO {

    public TaskResponseDTO(Integer id, String name, String reporter) {
        super(id, name);
        this.reporter = reporter;
    }

    private String reporter;

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String creatorId) {
        this.reporter = creatorId;
    }



}
