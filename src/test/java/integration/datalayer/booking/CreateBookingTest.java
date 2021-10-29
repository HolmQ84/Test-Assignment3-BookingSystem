package integration.datalayer.booking;

import com.github.javafaker.Faker;
import datalayer.BookingStorage.BookingStorage;
import datalayer.BookingStorage.BookingStorageImpl;
import dto.Booking.BookingCreation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Objects;
import java.sql.Date;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("integration")
public class CreateBookingTest {
    private BookingStorage bookingStorage;

    @BeforeAll
    public void Setup() throws SQLException {
        bookingStorage = new BookingStorageImpl("jdbc:mysql://localhost:3306/bookingsystemtest", "Test1234", "Test1234");

        var numBookings = bookingStorage.getBookings().size();
        if (numBookings < 50) {
            addFakeBookings(50 - numBookings);
        }
    }

    private void addFakeBookings(int numBookings) throws SQLException {
        Faker faker = new Faker();

        Long timer = faker.date().birthday().getTime();
        for (int i = 0; i < numBookings; i++) {

            BookingCreation b = new BookingCreation(
                    faker.number().numberBetween(1, 100),
                    faker.number().numberBetween(1, 20),
                    new Date(faker.date().birthday().getTime()),
                    new Time(timer),
                    new Time(timer * 7200)
            );
            bookingStorage.createBooking(b);
        }
    }

    @Test
    public void MustSaveBookingInDatabaseWhenCallingCreateBooking() throws SQLException {
        bookingStorage.createBooking(new BookingCreation(
                1,
                2,
                Date.valueOf("2021-12-10"),
                Time.valueOf("10:00:00"),
                Time.valueOf("12:00:00")
        ));

        var bookings = bookingStorage.getBookings();
        assertTrue(
                bookings.stream().anyMatch(x ->
                        x.getCustomerId() == 1 &&
                                x.getEmployeeId() == 2 &&
                                x.getDay().toString().equals("2021-12-10") &&
                                x.getStart().toString().equals("10:00:00") &&
                                x.getEnd().toString().equals("12:00:00")
                )
        );

    }

}