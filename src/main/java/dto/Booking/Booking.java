package dto.Booking;

import java.sql.Time;
import java.util.Date;


public class Booking {
    private final int id, customerId, employeeId;
    private final Date day;
    private final Time start;
    private final Time end;

    public Booking(int id, int customerId, int employeeId, Date day, Time start, Time end) {
        this.id = id;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.day = day;
        this.start = start;
        this.end = end;
    }

    public int getId() {
        return id;
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
