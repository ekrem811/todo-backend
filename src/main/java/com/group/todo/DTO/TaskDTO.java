package com.group.todo.DTO;

public class TaskDTO {

    private String name;
    private Integer statusID;

    public TaskDTO(String name, Integer statusID) {
        this.name = name;
        this.statusID = statusID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatusID() {
        return statusID;
    }

    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
    }

}
