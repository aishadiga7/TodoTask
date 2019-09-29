package com.example.todo.data.remote.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class TodoResponse {
    @JsonProperty("id")
    private int id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("scheduledDate")
    private String scheduledDate;
    @JsonProperty("status")
    private String status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskDescription() {
        return description;
    }

    public void setTaskDescription(String taskDescription) {
        this.description = taskDescription;
    }

    public String getTaskScheduledDate() {
        return scheduledDate;
    }

    public void setTaskScheduledDate(String taskScheduledDate) {
        this.scheduledDate = taskScheduledDate;
    }

    public String getTaskStatus() {
        return status;
    }

    public void setTaskStatus(String taskStatus) {
        this.status = taskStatus;
    }
}
