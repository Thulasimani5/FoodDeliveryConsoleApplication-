package com.foodapp.model;

public abstract class User {
    protected int userId;
    protected String name;
    protected String email;
    protected String phoneNumber;
    protected String password;
    protected Role role;

    public enum Role {
        Customer,
        RestaurantOwner
    }

    public User(int userId,
                String email,
                String password,
                Role role) {

        this.userId = userId;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNumber = phoneNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNo() {
        return phoneNumber;
    }
}