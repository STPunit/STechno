package com.example.desktop.stechno;

public class proAdd {
    String TaskDate;
    String TaskName;
    String TaskNumber;
    String TaskArea;
    String TaskAreaLine;
    String TaskServiceType;
    String TaskServiceInfo;
    String TaskAssignedTo;
    String TaskStatus;
    String TaskPriority;
    String TaskBilled;
    String TaskPaymentStatus;
    String TaskRemarks;
    String TaskSignature;
    String TaskId;


    private proAdd() {

    }

    public proAdd(String taskDate, String taskName, String taskNumber, String taskArea, String taskAreaLine, String taskServiceType, String taskServiceInfo, String taskAssignedTo, String taskStatus, String taskPriority, String taskBilled, String taskPaymentStatus, String taskRemarks, String taskSignature, String taskId) {
        this.TaskDate = taskDate;
        this.TaskName = taskName;
        this.TaskNumber = taskNumber;
        this.TaskArea = taskArea;
        this.TaskAreaLine = taskAreaLine;
        this.TaskServiceType = taskServiceType;
        this.TaskServiceInfo = taskServiceInfo;
        this.TaskAssignedTo = taskAssignedTo;
        this.TaskStatus = taskStatus;
        this.TaskPriority = taskPriority;
        this.TaskBilled = taskBilled;
        this.TaskPaymentStatus = taskPaymentStatus;
        this.TaskRemarks = taskRemarks;
        this.TaskSignature = taskSignature;
        this.TaskId = taskId;
    }

    private String getTaskDate() {
        return TaskDate;
    }

    private String getTaskName() {
        return TaskName;
    }

    private String getTaskNumber() {
        return TaskNumber;
    }

    private String getTaskArea() {
        return TaskArea;
    }

    private String getTaskAreaLine() {
        return TaskAreaLine;
    }

    private String getTaskServiceType() {
        return TaskServiceType;
    }

    private String getTaskServiceInfo() {
        return TaskServiceInfo;
    }

    private String getTaskAssignedTo() {
        return TaskAssignedTo;
    }

    private String getTaskStatus() {
        return TaskStatus;
    }

    private String getTaskPriority() {
        return TaskPriority;
    }

    private String getTaskBilled() {
        return TaskBilled;
    }

    private String getTaskPaymentStatus() {
        return TaskPaymentStatus;
    }

    private String getTaskRemarks() {
        return TaskRemarks;
    }

    private String getTaskSignature() {
        return TaskSignature;
    }

    private String getTaskId() {
        return TaskId;
    }
}

