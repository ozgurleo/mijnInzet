package com.mijninzet.projectteamdrie.model.entity.user;

public class Teacher extends User {
    public Teacher(String username, String password) {
        super(username, password, Role.TEACHER);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
