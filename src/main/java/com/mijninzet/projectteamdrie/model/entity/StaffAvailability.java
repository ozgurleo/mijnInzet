package com.mijninzet.projectteamdrie.model.entity;

//import com.mijninzet.projectteamdrie.model.entity.user.Teacher;
import com.mijninzet.projectteamdrie.model.entity.user.User;
import javax.persistence.*;

@Entity
public class StaffAvailability {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String day;
    private String dayPart;
    private String colorOption;
    private String cohort;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id")
    private User user;

    public StaffAvailability() {

    }


    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDayPart() {
        return dayPart;
    }

    public void setDayPart(String dayPart) {
        this.dayPart = dayPart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void setColorOption(String colorOption) {
        this.colorOption = colorOption;
    }

    public String getColorOption() {
        return colorOption;
    }
}
