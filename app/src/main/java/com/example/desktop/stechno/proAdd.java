package com.example.desktop.stechno;

public class proAdd {
    String TaskDate;
    String TaskTime;
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


    public proAdd() {

    }

    public proAdd(String taskDate, String taskTime, String taskName, String taskNumber, String taskArea, String taskAreaLine, String taskServiceType, String taskServiceInfo, String taskAssignedTo, String taskStatus, String taskPriority, String taskBilled, String taskPaymentStatus, String taskRemarks, String taskSignature, String taskId) {
        TaskDate = taskDate;
        TaskTime = taskTime;
        TaskName = taskName;
        TaskNumber = taskNumber;
        TaskArea = taskArea;
        TaskAreaLine = taskAreaLine;
        TaskServiceType = taskServiceType;
        TaskServiceInfo = taskServiceInfo;
        TaskAssignedTo = taskAssignedTo;
        TaskStatus = taskStatus;
        TaskPriority = taskPriority;
        TaskBilled = taskBilled;
        TaskPaymentStatus = taskPaymentStatus;
        TaskRemarks = taskRemarks;
        TaskSignature = taskSignature;
        TaskId = taskId;
    }

    private String getTaskDate() {
        return TaskDate;
    }

    private String getTaskTime() {
        return TaskTime;
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

