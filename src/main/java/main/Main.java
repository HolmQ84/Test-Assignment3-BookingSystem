package main;

import datalayer.CustomerStorage.CustomerStorageImpl;
import datalayer.EmployeeStorage.EmployeeStorageImpl;
import dto.Customer.Customer;
import dto.employee.Employee;

import java.sql.SQLException;

public class Main {

    private static final String conStr = "jdbc:mysql://localhost:3306/bookingsystemtest";
    private static final String user = "Test1234";
    private static final String pass = "Test1234";

    public static void main(String[] args) throws SQLException {
        CustomerStorageImpl storage = new CustomerStorageImpl(conStr, user, pass);
        EmployeeStorageImpl employeeStorage = new EmployeeStorageImpl(conStr, user, pass);

        System.out.println("Got customers: ");
        for(Customer c : storage.getCustomers()) {
            System.out.println(toString(c));
        }

        System.out.println("\nGot Employees: ");
        for (Employee e : employeeStorage.getEmployees()){
            System.out.println(toString(e));
        }
        System.out.println("The end.");
    }


    public static String toString(Customer c) {
        return "{" + c.getId() + ", " + c.getFirstname() + ", " + c.getLastname() + "}";
    }
    public static String toString(Employee c) {
        return "{" + c.getId() + ", " + c.getFirstname() + ", " + c.getLastname() + "}";
    }
}
