package com.mijninzet.projectteamdrie.model.entity;

import com.mijninzet.projectteamdrie.model.entity.user.User;

import javax.persistence.*;
import java.io.Serializable;
@Embeddable
public class CompoundKeyStaffAvailibility implements Serializable {
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="time_table_id")
    private TimeTable timeTable;

    public CompoundKeyStaffAvailibility() {
    }

    public CompoundKeyStaffAvailibility(int id, User user, TimeTable timeTable) {
        this.id = id;
        this.user = user;
        this.timeTable = timeTable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TimeTable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(TimeTable timeTable) {
        this.timeTable = timeTable;
    }
}
