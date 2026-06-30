//package com.foodapp.repository;
//
//import com.foodapp.model.Customer;
//import com.foodapp.model.RestaurantOwner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class OwnerStore {
//
//    private DataStore dataStore = DataStore.getInstance();
//
//    public void addOwner(RestaurantOwner owner) {
//        dataStore.getRestaurantOwners().put(owner.getEmail(), owner);
//    }
//
//    public List<RestaurantOwner> getRestaurantOwners()
//    {
//        return new ArrayList<>(dataStore.getRestaurantOwners().values());
//    }
//
//    public RestaurantOwner findByEmail(String email) {
//        return dataStore.getRestaurantOwners().get(email);
//    }
//}


package com.foodapp.repository;

import com.foodapp.model.RestaurantOwner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OwnerStore {

    private final Connection connection = DBConnection.getConnection();

    public boolean addOwner(RestaurantOwner owner) {

        String sql = "INSERT INTO RestaurantOwner " +
                "(user_id, name, email, phone_number, password, role) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, owner.getUserId());
            ps.setString(2, owner.getName());
            ps.setString(3, owner.getEmail());
            ps.setString(4, owner.getPhoneNo());
            ps.setString(5, owner.getPassword());
            ps.setString(6, owner.getRole().name());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<RestaurantOwner> getRestaurantOwners() {

        List<RestaurantOwner> owners = new ArrayList<>();

        String sql = "SELECT * FROM RestaurantOwner";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                RestaurantOwner owner = new RestaurantOwner(
                        rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getString("password")
                );

                owner.setName(rs.getString("name"));
                owner.setPhoneNo(rs.getString("phone_number"));

                owners.add(owner);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return owners;
    }

    public RestaurantOwner findByEmail(String email) {

        String sql = "SELECT * FROM RestaurantOwner WHERE email = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    RestaurantOwner owner = new RestaurantOwner(
                            rs.getInt("user_id"),
                            rs.getString("email"),
                            rs.getString("password")
                    );

                    owner.setName(rs.getString("name"));
                    owner.setPhoneNo(rs.getString("phone_number"));

                    return owner;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}