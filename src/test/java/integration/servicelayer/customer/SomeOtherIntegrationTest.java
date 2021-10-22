package integration.servicelayer.customer;

import datalayer.CustomerStorage;
import datalayer.CustomerStorageImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import servicelayer.CustomerService;
import servicelayer.CustomerServiceException;
import servicelayer.CustomerServiceImpl;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SomeOtherIntegrationTest {

    private CustomerService svc;
    private CustomerStorage storage;

    @BeforeAll
    public void setup() {
        storage = new CustomerStorageImpl("jdbc:mysql://localhost:3306/bookingsystemtest", "Test1234", "Test1234");
        svc = new CustomerServiceImpl(storage);
    }

    @Test
    public void Stuff() throws CustomerServiceException, SQLException {
        // Arrange
        var id = svc.createCustomer("schmeep", "schmoop", Date.valueOf("1987-10-07"));

        // Act
        var c = svc.getCustomerById(id);

        // Assert
        assertEquals("schmeep", c.getFirstname());
        assertEquals("schmoop", c.getLastname());
    }

}
