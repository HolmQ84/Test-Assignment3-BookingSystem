package servicelayer.booking;

import datalayer.BookingStorage.BookingStorage;
import dto.Booking.Booking;
import dto.Booking.BookingCreation;

import java.sql.SQLException;

import java.sql.Time;
import java.util.Collection;
import java.sql.Date;

public class BookingServiceImpl implements BookingService{

    private BookingStorage bookingStorage;

    public BookingServiceImpl(BookingStorage bookingStorage) {
        this.bookingStorage = bookingStorage;
    }

    @Override
    public int createBooking(int customerId, int employeeId, Date day, Time start, Time end) throws BookingServiceException {
       try{
           return bookingStorage.createBooking(new BookingCreation(customerId, employeeId, day, start, end));
       } catch (SQLException throwables){
           throw new BookingServiceException(throwables.getMessage());
       }
    }

    @Override
    public Booking getBookingById(int id) throws SQLException {
        return bookingStorage.getBookingWithId(id);
    }

    @Override
    public Collection<Booking> getBookingByCustomerId(int customerId) {
        return null;
    }
}
