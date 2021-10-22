package servicelayer.Customer;

import dto.Customer.Customer;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public interface CustomerService {

    int createCustomer(String firstname, String lastname, Date birthdate) throws CustomerServiceException;
    Customer getCustomerById(int id) throws SQLException;
    Collection<Customer> getCustomerByFirstName(String firstname);
}
