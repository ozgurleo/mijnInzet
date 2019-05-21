package com.mijninzet.projectteamdrie.model.entity.user;

public class UserSingleton {
    private static final UserSingleton singletonInstance=new UserSingleton();

    // variable of type int
    private static int id;
    public static String testString="test string in singleton";

    // private constructor restricted to this class itself
    private UserSingleton(){
    }

    // static method to create instance of Singleton class
    public static synchronized UserSingleton getInstance(){
        return singletonInstance;
    }

    //getter and setter for id
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
}
