package com.mijninzet.projectteamdrie.model.entity.user;


import javax.persistence.*;

@Entity(name = "user")
@DiscriminatorColumn(name = "role",
    discriminatorType = DiscriminatorType.STRING)
public abstract class User implements Comparable<User>{
    @Id
    private int userID;
    private String username;
    private String password;
    //@ozgur
    // dit variable is verwijderd omdat in de role van elke user wordt gegeven via discriminator value zoals Teacher.
//    @Enumerated(EnumType.STRING)
//    private Role role;

private static final String DEFAULT = "unknown";
    public User() {
        this(DEFAULT, DEFAULT);
    }

    public User(int userID) {
        this.userID = userID;
    }

    public User(String username, String password) {
        this(username, password, null);
    }

    //all args
    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
//        this.role = role;
    }

    @Override
    public int compareTo(User o) {
        return this.username.compareTo(o.username);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
//
//    public Role getRole() {
//        return role;
//    }

//    public void setRole(Role role) {
//        this.role = role;
//    }
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String toString() {

        return String.format("User %s with ID: %d", username, userID);
    }

}
