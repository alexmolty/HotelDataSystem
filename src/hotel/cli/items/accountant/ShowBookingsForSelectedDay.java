package hotel.cli.items.accountant;

import hotel.app.resources.HotelApplContext;
import hotel.cli.items.HotelItem;
import hotel.model.Booking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ShowBookingsForSelectedDay extends HotelItem {
    public ShowBookingsForSelectedDay(HotelApplContext context) {
        super(context);
    }

    @Override
    public String displayName() {
        return "Show Booking for selected day";
    }

    @Override
    public void perform() {
        LocalDate selected = inOut.inputDate("Enter date in format: ", dateFormat);
        if (selected == null) return;
        List<Booking> bookings = infoService.getBookingsStartOn(selected);
        showBookings(bookings, "No bookings found for " + selected.format(DateTimeFormatter.ofPattern(dateFormat)));
    }
}
