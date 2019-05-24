package com.mijninzet.projectteamdrie.model.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cohort {

    @Id
    private int cohortId;

    private int beginDate;
    private int endDate;

    public Cohort() {}

    public Cohort(int cohortId, int beginDate, int endDate) {
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

    public int getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(int beginDate) {
        this.beginDate = beginDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
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
