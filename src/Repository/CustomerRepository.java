package Repository;

import Models.Customer;

public class CustomerRepository {

    private DataStore dataStore = DataStore.getInstance();

    public void addCustomer(Customer customer) {
        dataStore.getCustomers().add(customer);
    }

    public Customer findByEmail(String email) {
        for (Customer customer : dataStore.getCustomers()) {
            if (customer.getEmail().equals(email)) {
                return customer;
            }
        }
        return null;
    }

    public Customer findById(int customerId) {
        for (Customer customer : dataStore.getCustomers()) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }
        return null;
    }
}