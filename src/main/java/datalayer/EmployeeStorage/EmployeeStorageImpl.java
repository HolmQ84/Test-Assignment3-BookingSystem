package datalayer.EmployeeStorage;

import dto.employee.Employee;
import dto.employee.EmployeeCreation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeStorageImpl implements EmployeeStorage{
    private String connectionString;
    private String username, password;


    public EmployeeStorageImpl(String conStr, String user, String pass) {
        connectionString = conStr;
        username = user;
        password = pass;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString, username, password);
    }


    @Override
    public Employee getEmployeeWithId(int employeeId) throws SQLException {

        var sql = "SELECT ID, firstname, lastname, birthday FROM Employees WHERE id = ?";
        try (var con = getConnection();
        var stmt = con.prepareStatement(sql)){
            stmt.setInt(1, employeeId);

            try (var resultSet = stmt.executeQuery()){
                if (resultSet.next()){
                 var id = resultSet.getInt("ID");
                 var firstname = resultSet.getString("firstname");
                 var lastname = resultSet.getString("lastname");
                 return new Employee(id, firstname, lastname);
                }
                return null;
            }
        }
    }



    @Override
    public List<Employee> getEmployees() throws SQLException {

        try (var con = getConnection();
        var stmt = con.createStatement()){
            var results = new ArrayList<Employee>();

            try (ResultSet resultSet = stmt.executeQuery("SELECT ID, firstname, lastname FROM Employees")){

                while (resultSet.next()){
                    int id = resultSet.getInt("ID");
                    String firstname = resultSet.getString("firstname");
                    String lastname = resultSet.getString("lastname");

                    Employee e = new Employee(id, firstname, lastname);
                    results.add(e);
                }
            }
            return results;
        }
    }



    @Override
    public int createEmployee(EmployeeCreation employeeToCreate) throws SQLException {
        var sql = "INSERT INTO Employees(firstname, lastname) VALUES (?, ?)";

        try (var con = getConnection();
        var stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, employeeToCreate.getFirstname());
            stmt.setString(2, employeeToCreate.getLastname());

            stmt.executeUpdate();

            //Get newly created it, set on line 77.
            try (var resultSet = stmt.getGeneratedKeys()){
                resultSet.next();
                int newId = resultSet.getInt(1);
                return newId;
            }
        }

    }
}
