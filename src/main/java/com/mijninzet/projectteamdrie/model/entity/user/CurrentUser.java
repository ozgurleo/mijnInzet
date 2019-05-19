package com.mijninzet.projectteamdrie.model.entity.user;

public class CurrentUser {
    private static int userID = -1;
    private static String password;
    private static String username;
    private static Role role;

    public CurrentUser() {
    }

    public CurrentUser(int userId, String password, String username){
        this.userID = userId;
        this.password = password;
        this.username = username;
    }


    // getters en setters
    public static int getUserID() {
        return userID;
    }

    public static void setUserID(int userID) {
        CurrentUser.userID = userID;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        CurrentUser.password = password;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        CurrentUser.username = username;
    }

    public static Role getRole() {
        return role;
    }

    public static void setRole(Role role) {
        CurrentUser.role = role;
    }

    public static String getDescription() {
        String loggedInUser = String.format("De gegevens van de nu ingelogde gebruiker. \ngebruikers ID: %d\nGebruikernaam: %s. ",
                userID ,username);

        return loggedInUser + role.toString();
    }
}
