package com.mijninzet.projectteamdrie.model.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.time.LocalDate;

@Entity
public class Cohort {

    @Id
    private int cohortId;

    private LocalDate beginDate;
    private LocalDate endDate;

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

    @Override
    public String toString() {
        return "Cohort{" +
                "cohortId=" + cohortId +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                '}';
    }

}
