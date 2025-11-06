package com.example.twitxclone.model;

public class User {


    public static final String N_KEY  = "EMAILV";
    public static final String DOB_KEY  = "DOBV";
    private String email;
    private String dob;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
