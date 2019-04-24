package com.miijninzet.mijninzetprojectteamdrie.model.entity.user;

public class Manager extends User{

    public Manager(String username, String password) {
        super(username, password, Role.MANAGER);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
