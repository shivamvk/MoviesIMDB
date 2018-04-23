package com.example.shivamvk.moviesimdb;

public class User {

    String stUserName;
    String stUserEmail;
    String stUserId;

    public User(String stUserName, String stUserEmail, String stUserId) {
        this.stUserName = stUserName;
        this.stUserEmail = stUserEmail;
        this.stUserId = stUserId;
    }

    public String getStUserName() {
        return stUserName;
    }

    public String getStUserEmail() {
        return stUserEmail;
    }

    public String getStUserId() {
        return stUserId;
    }
}
