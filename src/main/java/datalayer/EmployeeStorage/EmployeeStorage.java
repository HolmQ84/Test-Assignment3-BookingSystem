package datalayer.EmployeeStorage;

import dto.employee.Employee;
import dto.employee.EmployeeCreation;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeStorage {
    public Employee getEmployeeWithId(int employeeId) throws SQLException;
    public List<Employee> getEmployees() throws SQLException;
    public int createEmployee(EmployeeCreation employeeCreation) throws SQLException;
}
