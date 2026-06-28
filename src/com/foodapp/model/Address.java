package com.foodapp.model;

public class Address {
    private int addressId;
    private String building_No;
    private String street;
    private String area;
    private String city;
    private String state;
    private String pinCode;

    public Address(int addressId, String buildingNo, String street, String area, String city, String state, String pinCode)
    {
        this.addressId = addressId;
        this.building_No = buildingNo;
        this.street = street;
        this.area = area;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getBuilding_No() {
        return building_No;
    }

    public String getStreet() {
        return street;
    }

    public String getArea() {
        return area;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setBuilding_No(String building_No) {
        this.building_No = building_No;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStreet(String street) {
        this.street = street;
    }

}
