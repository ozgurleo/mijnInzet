package com.mijninzet.projectteamdrie.model.entity;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
public class ApplicationBasket {
    @Id
    private int tId;
    private String tName;
    private int estHours;
    private String fName;
    private String date;
    private int availHours;

    public ApplicationBasket() {
        this.tId = 0;
        this.tName = "";
        this.estHours = 0;
        this.fName = "";
        this.date = "";
        this.availHours = 0;
    }

    public ApplicationBasket(int tId, String tName, int estHours, String fName, String date, int availHours) {
        this.tId = tId;
        this.tName = tName;
        this.estHours = estHours;
        this.fName = fName;
        this.date = date;
        this.availHours = availHours;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public int getEstHours() {
        return estHours;
    }

    public void setEstHours(int estHours) {
        this.estHours = estHours;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAvailHours() {
        return availHours;
    }

    public void setAvailHours(int availHours) {
        this.availHours = availHours;
    }
}
