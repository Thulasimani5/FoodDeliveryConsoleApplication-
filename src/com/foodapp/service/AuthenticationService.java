package com.foodapp.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.foodapp.repository.CustomerStore;
import com.foodapp.repository.OwnerStore;
import com.foodapp.repository.RestaurantStore;
import com.foodapp.model.*;
import com.foodapp.model.Restaurant.RestaurantType;

import java.time.LocalTime;

public class AuthenticationService {

    private CustomerStore customerRepository = new CustomerStore();
    private OwnerStore ownerRepository = new OwnerStore();
    private RestaurantStore restaurantRepository = new RestaurantStore();


    public void customerSignup(
            String name,
            String phone,
            String email,
            String password,
            String buildingNo,
            String street,
            String area,
            String city,
            String state,
            String pinCode) {

        int id = customerRepository.getCustomers().size() + 1;
        int addId = customerRepository.getCustomers().size() + 1;

        Address address = new Address(
                addId,
                buildingNo,
                street,
                area,
                city,
                state,
                pinCode
        );

        Customer customer = new Customer(
                id,
                email,
                password
        );
        customer.setName(name);
        customer.setPhoneNo(phone);
        customer.setAddress(address);
        customerRepository.addCustomer(customer);
    }

    public int ownerSignup(
            String name,
            String phone,
            String email,
            String password,
            String restaurantName,
            String buildingNo,
            String street,
            String area,
            String city,
            String state,
            String pinCode,
            RestaurantType cuisineType,
            LocalTime openingTime,
            LocalTime closingTime) {

        int ownerId = ownerRepository.getRestaurantOwners().size() + 1;
        int restaurantId = restaurantRepository.getRestaurants().size() + 1;
        int addId = customerRepository.getCustomers().size() + 1;

        Address address = new Address(
                addId,
                buildingNo,
                street,
                area,
                city,
                state,
                pinCode
        );

        RestaurantOwner owner = new RestaurantOwner(
                ownerId,
                email,
                password
        );

        owner.setName(name);
        owner.setPhoneNo(phone);

        Restaurant restaurant = new Restaurant(
                restaurantId,
                restaurantName,
                address,
                owner
        );

        restaurant.setCuisineType(cuisineType);
        restaurant.setOpeningTime(openingTime);
        restaurant.setClosingTime(closingTime);

        owner.setRestaurant(restaurant);

        restaurantRepository.addRestaurant(restaurant);
        ownerRepository.addOwner(owner);

        return ownerId;
    }



    public int customerLogin(String email, String password) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null) {
            if (customer.getPassword().equals(password)) {
                return 1;
            }
            return -1;
        }
        return 0;
    }

    public int ownerLogin(String email, String password) {
        RestaurantOwner restaurantOwner = ownerRepository.findByEmail(email);
        if (restaurantOwner != null) {
            if (restaurantOwner.getPassword().equals(password)) {
                return 1;
            }
            return -1;
        }
        return 0;
    }


    public boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

     public boolean isValidPhone(String  phone) {
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }
        return phone.length() == 10 && (phone.charAt(0) == '9' || phone.charAt(0) == '8' || phone.charAt(0) == '7');
    }


    public boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public boolean emailExists(String email) {

        for (Customer c : customerRepository.getCustomers()) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }

        for (RestaurantOwner o : ownerRepository.getRestaurantOwners()) {
            if (o.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }

        return false;
    }

    public boolean phoneExists(String phoneNo) {

        for (Customer c : customerRepository.getCustomers()) {
            if (c.getPhoneNo().equals(phoneNo) ){
                return true;
            }
        }

        for (RestaurantOwner o : ownerRepository.getRestaurantOwners()) {
            if (o.getPhoneNo().equals(phoneNo)) {
                return true;
            }
        }

        return false;
    }

    public  boolean isValidPassword(String password) {

        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char ch : password.toCharArray()) {

            if (Character.isUpperCase(ch)) {
                hasUpper = true;
            } else if (Character.isLowerCase(ch)) {
                hasLower = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else {
                hasSpecial = true;
            }
        }

        return hasUpper && hasLower && hasDigit && hasSpecial;
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found.", e);
        }
    }
    }

