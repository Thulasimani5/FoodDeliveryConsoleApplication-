package Models;

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
}
