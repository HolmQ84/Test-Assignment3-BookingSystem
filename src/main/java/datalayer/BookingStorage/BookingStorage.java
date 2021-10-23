package datalayer.BookingStorage;

import dto.Booking.Booking;
import dto.Booking.BookingCreation;

import java.sql.SQLException;
import java.util.List;

public interface BookingStorage {
    public Booking getBookingWithId(int bookingId) throws SQLException;
    public List<Booking> getBookings() throws SQLException;
  //  public int createBooking(BookingCreation bookingCreation) throws SQLException;
}
