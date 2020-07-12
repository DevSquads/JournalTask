package com.example.journaltask.modules;

public class users {
    private boolean admin;
    private String password,userName;

    public users(boolean admin, String password, String userName) {
        this.admin = admin;
        this.password = password;
        this.userName = userName;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }
}
