//package com.foodapp.repository;
//
//import com.foodapp.model.Customer;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CustomerStore {
//
//    private DataStore dataStore = DataStore.getInstance();
//
//    public void addCustomer(Customer customer) {
//        dataStore.getCustomers().put(customer.getEmail(), customer);
//    }
//
//    public List<Customer> getCustomers()
//    {
//        return new ArrayList<>(dataStore.getCustomers().values());
//    }
//
//    public Customer findByEmail(String email) {
//        return dataStore.getCustomers().get(email);
//    }
//
//}

package com.foodapp.repository;

import com.foodapp.model.Address;
import com.foodapp.model.Customer;
import com.foodapp.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerStore {

    private final Connection connection = DBConnection.getConnection();

    public boolean addCustomer(Customer customer) {

        String sql = "INSERT INTO Customer " +
                "(user_id, name, email, phone_number, password, role, " +
                "building_number, street, area, city, state, pin_code) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, customer.getUserId());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPhoneNo());
            ps.setString(5, customer.getPassword());
            ps.setString(6, customer.getRole().name());

            ps.setString(7, customer.getAddress().getBuildingNumber());
            ps.setString(8, customer.getAddress().getStreet());
            ps.setString(9, customer.getAddress().getArea());
            ps.setString(10, customer.getAddress().getCity());
            ps.setString(11, customer.getAddress().getState());
            ps.setString(12, customer.getAddress().getPinCode());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Get All Customers
    public List<Customer> getCustomers() {

        List<Customer> customers = new ArrayList<>();

        String sql = "SELECT * FROM Customer";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Customer customer = new Customer(
                        rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getString("password")
                );

                customer.setName(rs.getString("name"));
                customer.setPhoneNo(rs.getString("phone_number"));
                customer.setRole(User.Role.Customer.valueOf(rs.getString("role")));
                Address address = new Address(
                        0, // or the actual addressId if you store it
                        rs.getString("building_number"),
                        rs.getString("street"),
                        rs.getString("area"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("pin_code")
                );

                customer.setAddress(address);
                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    // Find Customer By Email
    public Customer findByEmail(String email) {

        String sql = "SELECT * FROM Customer WHERE email = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Customer customer = new Customer(
                        rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getString("password")
                );


                customer.setName(rs.getString("name"));
                customer.setPhoneNo(rs.getString("phone_number"));
                customer.setRole(User.Role.Customer.valueOf(rs.getString("role")));

                Address address = new Address(
                        0, // or the actual addressId if you store it
                        rs.getString("building_number"),
                        rs.getString("street"),
                        rs.getString("area"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("pin_code")
                );

                customer.setAddress(address);

                return customer;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}