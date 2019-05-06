package com.mijninzet.projectteamdrie.model.entity;

import com.mijninzet.projectteamdrie.model.entity.user.Teacher;
import com.mijninzet.projectteamdrie.model.entity.user.User;

import javax.persistence.*;

@Entity
public class StaffAvailability {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String day;
    private String dayPart;
    private String colorOption;
    private String cohort;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
    @JoinColumn(name="user_id")
    private User user;

    public StaffAvailability() {

    }


    public StaffAvailability(int id, String day, String dayPart, String colorOption, String cohort, int userID) {
        this.id = id;
        this.day = day;
        this.dayPart = dayPart;
        this.colorOption = colorOption;
        this.cohort = cohort;
        this.user = new Teacher(userID);
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDayPart() {
        return dayPart;
    }

    public void setDayPart(String dayPart) {
        this.dayPart = dayPart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void setColorOption(String colorOption) {
        this.colorOption = colorOption;
    }

    public String getColorOption() {
        return colorOption;
    }
}
