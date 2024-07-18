package com.group.todo.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatusResponseDTO extends StatusRequestDTO{
    
    public StatusResponseDTO(Integer id, String name) {
        super(id, name);
    }

}
