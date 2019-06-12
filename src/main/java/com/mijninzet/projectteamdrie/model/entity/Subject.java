package com.mijninzet.projectteamdrie.model.entity;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int subjectId;
    private String subjectName;
    private int estimatedHours;
    private int yearsToExpiryDate;
    private String explanation;

    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "cohort_subject", joinColumns = @JoinColumn(name = "subject_id"), inverseJoinColumns = @JoinColumn(name = "cohort_id"))
    private Set<Cohort> cohort;


    public Subject() {
        this(-1, "", -1, -1);
    }

    public Subject(int subjectId, String subjectName, int estimatedHours, int yearsToExpiryDate) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.estimatedHours = estimatedHours;
        this.yearsToExpiryDate = yearsToExpiryDate;
    }

    public Subject(int subjectId, String subjectName, int estimatedHours, int yearsToExpiryDate, String explanation, Cohort...cohorts) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.estimatedHours = estimatedHours;
        this.yearsToExpiryDate = yearsToExpiryDate;
        this.explanation = explanation;
        this.cohort = Stream.of(cohorts).collect(Collectors.toSet());
        this.cohort.forEach(x-> x.getSubjects().add(this));
    }

    public Set<Cohort> getCohort() {
        return cohort;
    }

    public void setCohort(Set<Cohort> cohort) {
        this.cohort = cohort;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
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
