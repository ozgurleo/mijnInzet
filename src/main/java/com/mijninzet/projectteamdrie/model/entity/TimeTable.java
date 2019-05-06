package com.mijninzet.projectteamdrie.model.entity;

import com.mijninzet.projectteamdrie.model.entity.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "TimeTable")
public class TimeTable {
    @Id
    private int id;
    private int jaar;
    private int semester;
    private int bloknr;

    @ManyToMany
    @JoinTable(
            name = "StaffAvailibility",
            joinColumns = @JoinColumn(name = "time_table_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")

    )
    private List<User> users;

    public TimeTable(int id, int jaar, int semester, int bloknr) {
        this.id = id;
        this.jaar = jaar;
        this.semester = semester;
        this.bloknr = bloknr;
    }

    public TimeTable() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJaar() {
        return jaar;
    }

    public void setJaar(int jaar) {
        this.jaar = jaar;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getBloknr() {
        return bloknr;
    }

    public void setBloknr(int bloknr) {
        this.bloknr = bloknr;
    }

    @Override
    public String toString() {
        return "TimeTable{" +
                "id=" + id +
                ", jaar=" + jaar +
                ", semester=" + semester +
                ", bloknr=" + bloknr +
                '}';
    }
}
