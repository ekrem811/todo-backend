package com.group.todo.DTO;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TaskResponseDTO extends TaskRequestDTO {

    public TaskResponseDTO(Integer id, String name, Integer reporter) {
        super(id, name);
        this.reporter = reporter;
    }

    private Integer reporter;

    public Integer getReporter() {
        return reporter;
    }

    public void setReporter(Integer creatorId) {
        this.reporter = creatorId;
    }



}
