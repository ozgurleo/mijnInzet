package com.mijninzet.projectteamdrie.model.entity;

// this class is needed to have Model for opbatining data from multiple tables within mysqlDB
public class ApplicationBasket {
   private int tId;
    private String tName;
    private int estHours;
    private String fName;
    private String date;
    private int availHours;

    public ApplicationBasket() {
        this.tId = tId;
        this.tName = tName;
        this.estHours = estHours;
        this.fName = fName;
        this.date = date;
        this.availHours = availHours;
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
