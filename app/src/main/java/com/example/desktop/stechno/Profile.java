package com.example.desktop.stechno;

public class Profile {
    private String TaskName;
    private String TaskPriority;
    private String TaskAssignedTo;
    private String TaskServiceType;
    private String TaskId;

    public Profile() {
    }

    public Profile(String taskName, String taskPriority, String taskAssignedTo, String taskServiceType, String taskId) {
        this.TaskName = taskName;
        this.TaskPriority = taskPriority;
        this.TaskAssignedTo = taskAssignedTo;
        this.TaskServiceType = taskServiceType;
        this.TaskId = taskId;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        this.TaskName = taskName;
    }

    public String getTaskPriority() {
        return TaskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.TaskPriority = taskPriority;
    }

    public String getTaskAssignedTo() {
        return TaskAssignedTo;
    }

    public void setTaskAssignedTo(String taskAssignedTo) {
        this.TaskAssignedTo = taskAssignedTo;
    }

    public String getTaskServiceType() {
        return TaskServiceType;
    }

    public void setTaskServiceType(String taskServiceType) {
        this.TaskServiceType = taskServiceType;
    }
    public String getTaskId(){
        return TaskId;
    }

    public void setTaskId(String taskId) {
        this.TaskId = taskId;
    }
}
