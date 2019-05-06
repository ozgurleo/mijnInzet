package com.mijninzet.projectteamdrie.model.entity;

import com.mijninzet.projectteamdrie.model.entity.Task;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
@Entity
public class TaskApplication {
    @Id
    private int userId;
    private Date applicationDate;
    private Date unsubcribeDate;
    private int availableHours;
    private String role;
    @ManyToOne
    @JoinColumn(name="task_task_id")
    private Task task;

    public TaskApplication() {
        super();
        this.task = new Task();
        this.userId = userId;
        this.applicationDate = applicationDate;
        this.unsubcribeDate = unsubcribeDate;
        this.availableHours = availableHours;
        this.role = role;
    }

    public TaskApplication(int userId, Date applicationDate, Date unsubcribeDate, int availableHours, String role) {
        this.task=new Task();
        this.userId = userId;
        this.applicationDate = applicationDate;
        this.unsubcribeDate = unsubcribeDate;
        this.availableHours = availableHours;
        this.role = role;

    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getUnsubcribeDate() {
        return unsubcribeDate;
    }

    public void setUnsubcribeDate(Date unsubcribeDate) {
        this.unsubcribeDate = unsubcribeDate;
    }

    public int getAvailableHours() {
        return availableHours;
    }

    public void setAvailableHours(int availableHours) {
        this.availableHours = availableHours;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
