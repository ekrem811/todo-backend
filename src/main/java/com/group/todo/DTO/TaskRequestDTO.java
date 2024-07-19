package com.group.todo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDTO {

    private String name;
    private Integer assigneeId;
    private Integer statusId;

}
