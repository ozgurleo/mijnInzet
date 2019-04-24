package com.miijninzet.mijninzetprojectteamdrie.model.entity.user;

public class Administrator extends User {

    public Administrator(String username, String password) {
        super(username, password, Role.ADMINISTRATOR);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
