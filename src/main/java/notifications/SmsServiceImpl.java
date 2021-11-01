package notifications;

import datalayer.BookingStorage.BookingStorage;
import dto.SmsMessage;

public class SmsServiceImpl implements SmsService{

    public SmsServiceImpl(BookingStorage storageMock) {
    }

    @Override
    public boolean sendSms(SmsMessage message) {
        return true;
    }
}
