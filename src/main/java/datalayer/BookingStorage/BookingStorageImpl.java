package datalayer.BookingStorage;

import dto.Booking.Booking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BookingStorageImpl implements BookingStorage{
    private String connectionString;
    private String username, password;

    public BookingStorageImpl(String conStr, String user, String pass){
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
        var stmt =con.prepareStatement(sql)){
            stmt.setInt(1, bookingId);

            try (var resultSet = stmt.executeQuery()){
                if (resultSet.next()){
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
        var stmt = con.createStatement()){
            var results = new ArrayList<Booking>();

            try (ResultSet resultSet = stmt.executeQuery("SELECT * FROM Bookings")){
                while (resultSet.next()){
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
/*
    @Override
    public int createBooking() throws SQLException {
        return 0;
    }
 */
}
