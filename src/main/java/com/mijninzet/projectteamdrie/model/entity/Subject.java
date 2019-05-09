package com.mijninzet.projectteamdrie.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Subject")
public class Subject {

    @Id
    private int subjectId;
    private String subjectName;
    private int estimatedTimeInHours;
    private int yearsTillExpiry;

    public Subject() {
        this(0, "", 0, 0);
    }

    public Subject(int subjectId, String subjectName, int estimatedTimeInHours, int yearsTillExpiry) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.estimatedTimeInHours = estimatedTimeInHours;
        this.yearsTillExpiry = yearsTillExpiry;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getEstimatedTimeInHours() {
        return estimatedTimeInHours;
    }

    public void setEstimatedTimeInHours(int estimatedTimeInHours) {
        this.estimatedTimeInHours = estimatedTimeInHours;
    }

    public int getYearsTillExpiry() {
        return yearsTillExpiry;
    }

    public void setYearsTillExpiry(int yearsTillExpiry) {
        this.yearsTillExpiry = yearsTillExpiry;
    }
}
