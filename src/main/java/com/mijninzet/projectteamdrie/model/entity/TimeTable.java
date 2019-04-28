package com.mijninzet.projectteamdrie.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tijdtabel")
public class TimeTable {
    @Id
    private int id;
    private int jaar;
    private int semester;
    private int bloknr;

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

    public int getYear() {
        return jaar;
    }

    public void setYear(int year) {
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
                ", year=" + jaar +
                ", semester=" + semester +
                ", bloknr=" + bloknr +
                '}';
    }
}
