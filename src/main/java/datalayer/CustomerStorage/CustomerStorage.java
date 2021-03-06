package datalayer.CustomerStorage;

import dto.Customer.Customer;
import dto.Customer.CustomerCreation;

import java.sql.SQLException;
import java.util.List;

public interface CustomerStorage {
    public Customer getCustomerWithId(int customerId) throws SQLException;
    public List<Customer> getCustomers() throws SQLException;
    public int createCustomer(CustomerCreation customerCreation) throws SQLException;
}
