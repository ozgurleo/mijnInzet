package com.mijninzet.projectteamdrie.model.entity;

import com.mijninzet.projectteamdrie.model.entity.user.User;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int year;
    private int semester;
    private int bloknr;


    public TimeTable(int id, int year, int semester, int bloknr) {
        this.id = id;
        this.year = year;
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
        return year;
    }

    public void setYear(int jaar) {
        this.year = year;
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
                ", year=" + year +
                ", semester=" + semester +
                ", bloknr=" + bloknr +
                '}';
    }
}
