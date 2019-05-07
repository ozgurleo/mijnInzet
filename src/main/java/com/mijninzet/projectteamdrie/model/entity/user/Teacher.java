package com.mijninzet.projectteamdrie.model.entity.user;


import com.mijninzet.projectteamdrie.model.entity.StaffAvailability;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("teacher")
public class Teacher extends User{
    @OneToMany(mappedBy = "user")
    private Set<StaffAvailability>staffAvailabilities=new HashSet<>();


    public Teacher() {
    }

    public Teacher(int userID) {
        super(userID);
    }

}
