//package com.mijninzet.projectteamdrie.model.entity;
//
//import com.mijninzet.projectteamdrie.model.entity.user.Teacher;
//import com.mijninzet.projectteamdrie.model.entity.user.User;
//
//import javax.persistence.*;
//
//
//public class StaffAvailability {
//    @Id
//    private int id;
//    private String day;
//    private String dayPart;
//    private String colorOption;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name="user_id")
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name="time_table_id")
//    private TimeTable timeTable;
//
//    public StaffAvailability() {
//        this.id = 0;
//        this.day = "";
//        this.dayPart = "";
//        this.user = new Teacher();
//        this.timeTable = new TimeTable();
//        this.colorOption="";
//
//    }
//
//    public StaffAvailability(int id, String day, String dayPart, int userID, String colorOption, TimeTable timeTable) {
//        this.id = id;
//        this.day = day;
//        this.dayPart = dayPart;
//        this.user = new Teacher(userID);
//        this.timeTable = timeTable;
//        this.colorOption=colorOption;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getDay() {
//        return day;
//    }
//
//    public void setDay(String day) {
//        this.day = day;
//    }
//
//    public String getDayPart() {
//        return dayPart;
//    }
//
//    public void setDayPart(String dayPart) {
//        this.dayPart = dayPart;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
////
////    public ColorOption getColorOption() {
////        return colorOption;
////    }
////
////    public void setColorOption(ColorOption colorOption) {
////        this.colorOption = colorOption;
////    }
//
//    public TimeTable getTimeTable() {
//        return timeTable;
//    }
//
//    public void setTimeTable(TimeTable timeTable) {
//        this.timeTable = timeTable;
//    }
//
//    public void setColorOption(String colorOption) {
//        this.colorOption = colorOption;
//    }
//
//    public String getColorOption() {
//        return colorOption;
//    }
//}
