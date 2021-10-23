package dto.Booking;

import java.sql.Time;
import java.util.Date;

public class BookingCreation {

    public final Date day;
    public final Time start;
    public final Time end;

    public BookingCreation(Date day, Time start, Time end) {
        this.day = day;
        this.start = start;
        this.end = end;
    }


    public Date getDay(){
        return day;
    }

    public Time getStart(){
        return start;
    }
    public Time getEnd(){
        return end;
    }
}
