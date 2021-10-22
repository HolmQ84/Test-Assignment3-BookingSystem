package integration.datalayer.employee;

import com.github.javafaker.Faker;
import datalayer.EmployeeStorage.EmployeeStorage;
import datalayer.EmployeeStorage.EmployeeStorageImpl;
import dto.employee.EmployeeCreation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("integration")
public class CreateEmployeeTest {
    private EmployeeStorage employeeStorage;

    @BeforeAll
    public void Setup() throws SQLException {

        employeeStorage = new EmployeeStorageImpl("jdbc:mysql://localhost:3306/bookingsystemtest", "Test1234", "Test1234");

        var numEmployee = employeeStorage.getEmployees().size();
        if (numEmployee < 20) {
            addFakeEmployees(20 - numEmployee);
        }
    }


    private void addFakeEmployees(int numEmployees) throws SQLException {
        Faker faker = new Faker();
        for (int i = 0; i < numEmployees; i++) {
            EmployeeCreation e = new EmployeeCreation(faker.funnyName().name(), faker.animal().name());
            employeeStorage.createEmployee(e);
        }
    }

    @Test
    public void mustSaveCustomerInDatabaseWhenCallingCreateEmployee() throws SQLException {
        // Arrange
        // Act
        employeeStorage.createEmployee(new EmployeeCreation("Peter", "Petersen"));

        //Assert
        var employees = employeeStorage.getEmployees();
        assertTrue(
                employees.stream().anyMatch(x ->
                        x.getFirstname().equals("Peter") &&
                                x.getLastname().equals("Petersen")));
    }
}
