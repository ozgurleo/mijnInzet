package com.mijninzet.projectteamdrie.model.entity;

import javax.persistence.*;

@Entity
public class SubjectPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int userId;
    private String preference;

    @ManyToOne
    @JoinColumn(name = "subject_preference_id")
    private Subject subject;

    public SubjectPreference() { super(); }

    public SubjectPreference(String preference, Subject subject) {
        this.preference = preference;
        this.subject = subject;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
