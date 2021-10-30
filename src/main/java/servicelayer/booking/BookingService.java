package servicelayer.booking;

import dto.Booking.Booking;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Collection;
import java.sql.Date;

public interface BookingService {
    int createBooking(int customerId, int employeeId, Date day, Time start, Time end) throws BookingServiceException;
    Booking getBookingById(int id) throws SQLException;
    Collection<Booking> getBookingByCustomerId(int customerId);
}
