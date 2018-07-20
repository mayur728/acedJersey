package com.duotech.model;

public class Profile {

    private long id;
    private String profName;
    private String firstName;
    private String lastName;

    public void setId(long id) {
        this.id = id;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getId() {
        return id;
    }

    public Profile() {
    }

    public Profile(long id, String profName, String firstName, String lastName) {
        this.id = id;
        this.profName = profName;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public String getProfName() {
        return profName;
    }
}
