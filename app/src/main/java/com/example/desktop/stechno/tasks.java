package com.example.desktop.stechno;


public class tasks {

     String TaskName;
     String TaskPriority;
     String TaskAssignedTo;
     String TaskServiceType;


    public tasks(String taskName, String taskPriority, String taskAssignedTo, String taskServiceType) {
        TaskName = taskName;
        TaskPriority = taskPriority;
        TaskAssignedTo = taskAssignedTo;
        TaskServiceType = taskServiceType;
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
}
