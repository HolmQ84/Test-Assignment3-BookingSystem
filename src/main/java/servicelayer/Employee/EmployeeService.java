package servicelayer.Employee;

import dto.employee.Employee;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public interface EmployeeService {
    int createEmployee(String firstname, String lastname, Date birthdate) throws EmployeeServiceException;
    Employee getEmployeeById(int id) throws SQLException;
    Collection<Employee> getEmployeeByFirstname(String firstname);

}
