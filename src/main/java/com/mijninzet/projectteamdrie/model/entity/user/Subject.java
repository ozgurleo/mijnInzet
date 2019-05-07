package com.mijninzet.projectteamdrie.model.entity.user;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Subject")
public class Subject {

       @Id
    private int subjectId;
    private String subjectName;
    private int estimatedHours;
    private int yearsToExpiryDate;
//    @OneToMany(mappedBy = "task")
//    private List<TaskApplication> taskApplication;

    public Subject() {
        this(0, "", 0, 0);
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
}
