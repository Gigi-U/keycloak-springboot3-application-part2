package com.dh.msusers.model;

import lombok.*;

import java.util.List;


@Builder
public class User {
    private String id;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private List<BillDTO> bills;

    public User() {
    }
    public User(String id, String userName, String email, String firstName, String lastName, List<BillDTO> bills) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bills = bills;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<BillDTO> getBills() {
        return bills;
    }

    public void setBills(List<BillDTO> bills) {
        this.bills = bills;
    }
}