package unit.servicelayer.employee;

import datalayer.EmployeeStorage.EmployeeStorage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import servicelayer.Employee.EmployeeService;
import servicelayer.Employee.EmployeeServiceException;
import servicelayer.Employee.EmployeeServiceImpl;

import java.sql.SQLException;
import java.util.Date;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class CreateEmployeeTest {

    // SUT (System Under Test)
    private EmployeeService employeeService;

    // DOC (Dependend-on Component)
    private EmployeeStorage storageMock;

    @BeforeAll
    public void CreatingMocks(){
        storageMock = mock(EmployeeStorage.class);
        employeeService = new EmployeeServiceImpl(storageMock);
    }

    @Test
    public void ShallCallStorageWhenCreatingAEmployee() throws EmployeeServiceException, SQLException{
        // Arrange
        // Act

        var firstname = "EmployFirst";
        var lastname = "EmployLast";
        var birthdate = new Date(1234567890l);

        employeeService.createEmployee(firstname, lastname, birthdate);

        // Assert
        /* We can't verify first and last name with the strings above, while
        * it checks on an object, not a string. Therefore, this will not be verified
        */
        verify(storageMock, times(1))
                .createEmployee(argThat(x -> x.firstname.equals(firstname)
                && x.lastname.equals(lastname)));
    }
}
