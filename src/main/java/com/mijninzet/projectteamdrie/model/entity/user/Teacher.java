package com.mijninzet.projectteamdrie.model.entity.user;


import com.mijninzet.projectteamdrie.model.entity.TimeTable;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("teacher")
public class Teacher extends User{
    @ManyToMany
    @JoinTable(
            name = "StaffAvailibility",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "time_table_id")
    )
    private List<TimeTable>timeTables;
    public Teacher() {
    }

    public Teacher(int userID) {
        super(userID);
    }

}
