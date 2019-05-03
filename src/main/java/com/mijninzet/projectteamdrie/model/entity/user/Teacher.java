package com.mijninzet.projectteamdrie.model.entity.user;

import com.mijninzet.projectteamdrie.model.entity.StaffAvailability;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("teacher")
public class Teacher extends User{
    @OneToMany
    @JoinColumn(name="user_id")
    private List<StaffAvailability>staffAvailabilities;
    public Teacher() {
    }

    public Teacher(int userID) {
        super(userID);
    }

}
