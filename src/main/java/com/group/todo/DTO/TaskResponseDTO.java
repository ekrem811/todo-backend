package com.group.todo.DTO;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TaskResponseDTO extends TaskRequestDTO {

    public TaskResponseDTO(Integer id, String name, Integer creatorId) {
        super(id, name);
        this.creatorId = creatorId;
    }

    private Integer creatorId;

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }



}
