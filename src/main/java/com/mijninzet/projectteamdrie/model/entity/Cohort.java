package com.mijninzet.projectteamdrie.model.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cohort {

    @Id
    private int cohortId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Subject> subjects;


    public Cohort() {}

    public Cohort(int cohortId, LocalDate beginDate, LocalDate endDate) {
        this.cohortId = cohortId;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public int getCohortId() {
        return cohortId;
    }

    public void setCohortId(int cohortId) {
        this.cohortId = cohortId;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }


    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return " Cohort " +
                "  " + cohortId +
                " | vanaf " + beginDate +
                " tot " + endDate;
    }

}
