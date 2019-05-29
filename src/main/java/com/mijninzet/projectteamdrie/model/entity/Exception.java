package com.mijninzet.projectteamdrie.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mijninzet.projectteamdrie.UserSingleton;
import com.mijninzet.projectteamdrie.model.entity.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Exception {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int Id;
    @JsonFormat(pattern="dd-MM-yyyy")
    private String endDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private String startDate;
    private String colorOption;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name="user_id")
    private User user;

    public Exception() {
        final int userId = UserSingleton.getInstance().getId();
        this.user = new User(userId);
    }


    public Exception(String endDate, String startDate, String colorOption) {
        this.endDate = endDate;
        this.startDate = startDate;
        this.colorOption = colorOption;
        final int userId = UserSingleton.getInstance().getId();
        this.user = new User(userId);

    }

    public Exception(int userID) {
        this.user = new User(userID);
    }

//    public Exception(String endDate, String startDate, String colorOption) {
//        this.endDate = endDate;
//        this.startDate = startDate;
//        this.colorOption = colorOption;
//        final int userId = UserSingleton.getInstance().getId();
//        this.user = new User(userId);
//
//    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }


    public String getColorOption() {
        return colorOption;
    }

    public void setColorOption(String colorOption) {
        this.colorOption = colorOption;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Exception{" +
                "user=" + user +
                '}';
    }
}
