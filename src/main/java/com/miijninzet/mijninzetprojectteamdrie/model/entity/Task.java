package com.miijninzet.mijninzetprojectteamdrie.model.entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Task")
public class Task {

       @Id
    private int task_id;
    private String task_name;
    private int estimated_hours;
    private int years_to_expiry_date;

    public Task() {
        this(0, "", 0, 0);
    }

    public Task(int task_id, String task_name, int estimated_hours, int years_to_expiry_date) {
        this.task_id = task_id;
        this.task_name = task_name;
        this.estimated_hours = estimated_hours;
        this.years_to_expiry_date = years_to_expiry_date;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public int getEstimated_hours() {
        return estimated_hours;
    }

    public void setEstimated_hours(int estimated_hours) {
        this.estimated_hours = estimated_hours;
    }

    public int getYears_to_expiry_date() {
        return years_to_expiry_date;
    }

    public void setYears_to_expiry_date(int years_to_expiry_date) {
        this.years_to_expiry_date = years_to_expiry_date;
    }
}
