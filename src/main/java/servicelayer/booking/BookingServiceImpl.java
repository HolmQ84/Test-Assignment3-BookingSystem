package servicelayer.booking;

import datalayer.BookingStorage.BookingStorage;
import dto.Booking.Booking;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;

public class BookingServiceImpl implements BookingService{

    private BookingStorage bookingStorage;

    @Override
    public int createBooking(Date day, LocalTime start, LocalTime end) throws BookingServiceException {
        return 0;
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
