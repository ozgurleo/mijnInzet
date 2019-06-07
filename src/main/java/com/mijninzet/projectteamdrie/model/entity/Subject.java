package com.mijninzet.projectteamdrie.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    private int subjectId;
    private String subjectName;
    private int estimatedHours;
    private int yearsToExpiryDate;

    public Subject() {
        this(-1, "", -1, -1);
    }

    public Subject(int subjectId, String subjectName, int estimatedHours, int yearsToExpiryDate) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.estimatedHours = estimatedHours;
        this.yearsToExpiryDate = yearsToExpiryDate;
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

    public int getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(int estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public int getYearsToExpiryDate() {
        return yearsToExpiryDate;
    }

    public void setYearsToExpiryDate(int yearsToExpiryDate) {
        this.yearsToExpiryDate = yearsToExpiryDate;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", estimatedHours=" + estimatedHours +
                ", yearsToExpiryDate=" + yearsToExpiryDate +
                '}';
    }
}
