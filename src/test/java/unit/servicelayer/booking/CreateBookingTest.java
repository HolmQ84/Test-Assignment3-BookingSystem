package unit.servicelayer.booking;

import com.github.javafaker.Faker;
import datalayer.BookingStorage.BookingStorage;
import notifications.SmsService;
import notifications.SmsServiceImpl;
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

    private SmsService smsService;

    // DOC (Dependend-on Component)
    private BookingStorage storageMock;

    @BeforeAll
    public void CreatingMocks() {
        System.out.println("Creating mocks");
        storageMock = mock(BookingStorage.class);
        smsService = new SmsServiceImpl(storageMock);
        bookingService = new BookingServiceImpl(storageMock);
        System.out.println("Created mocks!");
    }

    @Test
    public void mustCallStorageWhenCreatingBookings() throws BookingServiceException, SQLException {
        Faker faker = new Faker();


        int customerId = faker.number().numberBetween(1, 100);
        int employeeId = faker.number().numberBetween(1, 20);
        Date day = Date.valueOf("2021-08-04");
        Time start = Time.valueOf("12:00:00");
        Time end = Time.valueOf("14:00:00");


        bookingService.createBooking(customerId, employeeId, day, start, end);
        System.out.println("We are here 2!");
        verify(storageMock, times(1))
                .createBooking(argThat(x -> x.customerId == customerId &&
                                x.employeeId == employeeId &&
                                x.day.toString().equals("2021-08-04") &&
                                x.start.toString().equals("12:00:00") &&
                                x.end.toString().equals("14:00:00")));

    }

    @Test
    public void mustSendSmsWhenCreatingBooking() throws BookingServiceException, SQLException {
        Faker faker = new Faker();

        int customerId = faker.number().numberBetween(1, 100);
        int employeeId = faker.number().numberBetween(1, 20);
        Date day = Date.valueOf("2021-08-04");
        Time start = Time.valueOf("12:00:00");
        Time end = Time.valueOf("14:00:00");

        bookingService.createBooking(customerId, employeeId, day, start, end);
        System.out.println("We are here 2!");
        verify(storageMock, times(1))
                .createBooking(argThat(x -> x.customerId == customerId &&
                        x.employeeId == employeeId &&
                        x.day.toString().equals("2021-08-04") &&
                        x.start.toString().equals("12:00:00") &&
                        x.end.toString().equals("14:00:00")));

    }
}