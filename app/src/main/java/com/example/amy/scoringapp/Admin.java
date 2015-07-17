package com.example.amy.scoringapp;

/**
 * Created by Amy on 7/16/2015.
 */
public class Admin {
    private String name;
    private String password;

    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public boolean equalTo(Admin b) {
        return (this.name == b.getName() && this.password == b.getPassword());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
