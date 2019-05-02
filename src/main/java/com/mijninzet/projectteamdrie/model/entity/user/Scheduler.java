package com.mijninzet.projectteamdrie.model.entity.user;

public class Scheduler extends User {

    public Scheduler(String username, String password) {
        super(username, password, Role.SCHEDULER);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
