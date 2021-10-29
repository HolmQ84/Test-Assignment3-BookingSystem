package dto.Booking;

import java.sql.Time;
import java.sql.Date;

public class BookingCreation {
    public final int customerId, employeeId;
    public final Date day;
    public final Time start;
    public final Time end;

    public BookingCreation(int customerId, int employeeId, Date day, Time start, Time end) {
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.day = day;
        this.start = start;
        this.end = end;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public Date getDay() {
        return day;
    }

    public Time getStart() {
        return start;
    }

    public Time getEnd() {
        return end;
    }
}
