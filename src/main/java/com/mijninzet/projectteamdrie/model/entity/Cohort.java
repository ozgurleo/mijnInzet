package com.mijninzet.projectteamdrie.model.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Cohort {

    @Id
    private int cohortId;

    private Date beginDate;
    private Date endDate;

    public Cohort() {}

    public Cohort(int cohortId, Date beginDate, Date endDate) {
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

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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
