package hotel.cli.items.guest;

import hotel.app.resources.HotelApplContext;
import hotel.cli.items.HotelItem;
import hotel.model.Booking;
import hotel.model.Guest;

import java.util.List;

public class ShowMyBookingsItem extends HotelItem {
    public ShowMyBookingsItem(HotelApplContext context) {
        super(context);
    }

    @Override
    public String displayName() {
        return "Show my bookings";
    }

    @Override
    public void perform() {
        Guest guest = getExistingGuest();
        if (guest == null) return;
        List<Booking> bookings = infoService.getBookingsByGuestsId(guest.getId());
        if(bookings.isEmpty()){
            inOut.outputLine("You have no bookings.");
            return;
        }
        inOut.outputLine("Your bookings: ");
        showBookings(bookings, "You have no bookings.");

    }
}
