package com.mijninzet.projectteamdrie.model.entity;

public class TimeTable {
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

    public void setYear(int year) {
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
