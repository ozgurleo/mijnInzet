package com.miijninzet.mijninzetprojectteamdrie.model.entity.user;

public class Coordinator extends User{

    public Coordinator(String username, String password) {
        super(username, password, Role.COORDINATOR);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
