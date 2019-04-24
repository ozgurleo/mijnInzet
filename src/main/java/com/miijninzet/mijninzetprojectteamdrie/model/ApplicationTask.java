package com.miijninzet.mijninzetprojectteamdrie.model;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;

@Entity
public class ApplicationTask {
    private int applicationID;
    private Date applictionDate;
    private Date signOutDate;
    private int wantedHours;

    @OneToMany
    private Task task;

}
