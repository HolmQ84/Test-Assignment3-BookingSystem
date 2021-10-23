package servicelayer.booking;

import dto.Booking.Booking;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;

public interface BookingService {
    int createBooking(Date day, LocalTime start, LocalTime end) throws BookingServiceException;
    Booking getBookingById(int id) throws SQLException;
    Collection<Booking> getBookingByCustomerId(int customerId);
}
