package hotel.items.guest;

import hotel.app.resources.HotelApplContext;
import hotel.items.HotelItem;
import hotel.model.Booking;
import hotel.model.Guest;
import hotel.model.Room;

import java.time.LocalDate;
import java.util.List;

public class CreateBookingItem extends HotelItem {
    public CreateBookingItem(HotelApplContext context) {
        super(context);
    }

    @Override
    public String displayName() {
        return "Create booking";
    }

    public void perform() {
        Guest guest = getExistingGuest();
        if (guest == null) return;
        LocalDate[] dates = inputCheckInCheckOut();
        if (dates == null) return;
        List<Room> availableRooms = infoService.getAvailableRoomsForDates(dates[0], dates[1]);
        if (availableRooms.isEmpty()) {
            inOut.outputLine("There is no available rooms for this dates.");
            return;
        }
        inOut.outputLine("There are " + availableRooms.size() + " available rooms for this dates.");
        showRooms(availableRooms, "No available rooms for this dates.");
        Room room = getExistingRoom();
        if (room == null) return;
        boolean selectedRoomAvailable = availableRooms.contains(room);
        if (!selectedRoomAvailable) {
            inOut.outputLine("Room " + room.getRoomNumber() + " is not available for this dates.");
            return;
        }
        Booking booking = managingService.createBooking(guest, room, dates[0], dates[1]);
        inOut.outputLine("Booking created: " + booking);
    }
}