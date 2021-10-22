package servicelayer.Employee;

import datalayer.EmployeeStorage.EmployeeStorage;
import dto.employee.Employee;
import dto.employee.EmployeeCreation;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeStorage employeeStorage;

    public EmployeeServiceImpl(EmployeeStorage employeeStorage) {
        this.employeeStorage = employeeStorage;
    }

    @Override
    public int createEmployee(String firstname, String lastname, Date birthdate) throws EmployeeServiceException {
        try {
            return employeeStorage.createEmployee(new EmployeeCreation(firstname, lastname));
        } catch (SQLException throwables){
            throw new EmployeeServiceException(throwables.getMessage());
        }
    }

    @Override
    public Employee getEmployeeById(int id) throws SQLException {
        return employeeStorage.getEmployeeWithId(id);
    }

    @Override
    public Collection<Employee> getEmployeeByFirstname(String firstname) {
        return null;
    }
}
