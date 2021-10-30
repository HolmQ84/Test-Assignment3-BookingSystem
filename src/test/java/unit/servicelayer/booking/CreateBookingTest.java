package unit.servicelayer.booking;

import com.github.javafaker.Faker;
import datalayer.BookingStorage.BookingStorage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import servicelayer.booking.BookingService;
import servicelayer.booking.BookingServiceException;
import servicelayer.booking.BookingServiceImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class CreateBookingTest {

    // SUT (System Under Test)
    private BookingService bookingService;

    // DOC (Dependend-on Component)
    private BookingStorage storageMock;

    @BeforeAll
    public void CreatingMocks() {
        System.out.println("Creating mocks");
        storageMock = mock(BookingStorage.class);
        bookingService = new BookingServiceImpl(storageMock);
        System.out.println("Created mocks!");
    }

    @Test
    public void mustCallStorageWhenCreatingCustomer() throws BookingServiceException, SQLException {
        Faker faker = new Faker();


        int customerId = faker.number().numberBetween(1, 100);
        int employeeId = faker.number().numberBetween(1, 20);
        Date day = Date.valueOf("2021-08-04");
        Time start = Time.valueOf("12:00:00");
        Time end = Time.valueOf("14:00:00");
        System.out.println("We are here!");


        bookingService.createBooking(customerId, employeeId, day, start, end);
        System.out.println("We are here 2!");
        verify(storageMock, times(1))
                .createBooking(argThat(x -> x.customerId == customerId &&
                                x.employeeId == employeeId &&
                                x.day.equals("2021-08-04") &&
                                x.start.equals("12:00:00") &&
                                x.end.equals("14:00:00")));

    }
}