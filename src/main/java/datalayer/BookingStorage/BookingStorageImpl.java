package datalayer.BookingStorage;

import dto.Booking.Booking;
import dto.Booking.BookingCreation;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class BookingStorageImpl implements BookingStorage {
    private String connectionString;
    private String username, password;

    public BookingStorageImpl(String conStr, String user, String pass) {
        connectionString = conStr;
        username = user;
        password = pass;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString, username, password);
    }

    @Override
    public Booking getBookingWithId(int bookingId) throws SQLException {

        var sql = "SELECT ID, customerId, employeeId, date, start, end FROM Bookings WHERE id = ?";
        try (var con = getConnection();
             var stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, bookingId);

            try (var resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    var id = resultSet.getInt("ID");
                    var customerId = resultSet.getInt("customerId");
                    var employeeId = resultSet.getInt("employeeId");
                    var day = resultSet.getDate("date");
                    var start = resultSet.getTime("start");
                    var end = resultSet.getTime("end");

                }
                return null;
            }
        }
    }

    @Override
    public List<Booking> getBookings() throws SQLException {
        try (var con = getConnection();
             var stmt = con.createStatement()) {
            var results = new ArrayList<Booking>();

            try (ResultSet resultSet = stmt.executeQuery("SELECT * FROM Bookings")) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    var customerId = resultSet.getInt("customerId");
                    var employeeId = resultSet.getInt("employeeId");
                    var day = resultSet.getDate("date");
                    var start = resultSet.getTime("start");
                    var end = resultSet.getTime("end");

                    Booking b = new Booking(id, customerId, employeeId, day, start, end);
                    results.add(b);
                }
            }
            return results;
        }
    }

    @Override
    public int createBooking(BookingCreation bookingToCreate) throws SQLException {
        var sql = "INSERT INTO Bookings(customerId, employeeId, date, start, end) VALUES (?, ?, ?, ?, ?)";
        try (var con = getConnection();
             var stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, bookingToCreate.getCustomerId());
            stmt.setInt(2, bookingToCreate.getEmployeeId());
            stmt.setDate(3, (Date) bookingToCreate.getDay());
            stmt.setTime(4, bookingToCreate.getStart());
            stmt.setTime(5, bookingToCreate.getEnd());

            stmt.executeUpdate();

            // Get the newly created id
            try (var resultSet = stmt.getGeneratedKeys()){
                resultSet.next();
                int newId = resultSet.getInt(1);
                return newId;
            }
        }
    }

}
