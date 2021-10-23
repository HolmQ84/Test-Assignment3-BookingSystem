package main;

import datalayer.BookingStorage.BookingStorageImpl;
import datalayer.CustomerStorage.CustomerStorageImpl;
import datalayer.EmployeeStorage.EmployeeStorageImpl;
import dto.Booking.Booking;
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
        BookingStorageImpl bookingStorage = new BookingStorageImpl(conStr, user, pass);

        System.out.println("Got customers: ");
        for(Customer c : storage.getCustomers()) {
            System.out.println(toString(c));
        }

        System.out.println("\nGot Employees: ");
        for (Employee e : employeeStorage.getEmployees()){
            System.out.println(toString(e));
        }

        System.out.println("\nGot Bookings: ");
        for (Booking b : bookingStorage.getBookings()){
            System.out.println(toString(b));
        }
        System.out.println("The end.");
    }


    public static String toString(Customer c) {
        return "{" + c.getId() + ", " + c.getFirstname() + ", " + c.getLastname() + "}";
    }
    public static String toString(Employee e) {
        return "{" + e.getId() + ", " + e.getFirstname() + ", " + e.getLastname() + "}";
    }
    public static String toString(Booking b) {
        return "{" + b.getId() + ", " + b.getCustomerId() + ", " + b.getEmployeeId() + ", " + b.getDay() + ", " + b.getStart() + ", " + b.getEnd() + "}";
    }
}
