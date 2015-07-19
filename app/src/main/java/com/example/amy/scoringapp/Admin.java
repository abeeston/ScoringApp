package com.example.amy.scoringapp;

/**
 * Contains a single administrator and their password, received from the Database.
 * Gives the ability to compare two administrators
 */
public class Admin {
    private String name;
    private String password;

    /**
     * Creates a single administrator
     * @param name Username
     * @param password Password
     */
    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * Compares two administrators
     * @param b The administrator being compared to
     * @return boolean
     */
    public boolean equalTo(Admin b) {
        return (this.name.equals(b.getName()) && this.password.equals(b.getPassword()));
    }

    /**
     * Gets the name
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name
     * @param name Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the password
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password
     * @param password Password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
