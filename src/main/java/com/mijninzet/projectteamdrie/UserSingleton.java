package com.mijninzet.projectteamdrie;

public final class UserSingleton {
    private static UserSingleton singletonInstance;

    // variable of type int
    private static int id;

    // private constructor restricted to this class itself
    private UserSingleton(int id) {
        this.id=id;
    }

    // static method to create instance of Singleton class
    public static UserSingleton getInstance(int id) {

                if (singletonInstance == null) {
                    singletonInstance = new UserSingleton(id);
                }


        return singletonInstance;
    }

    //getter and setter for id
    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }
}
