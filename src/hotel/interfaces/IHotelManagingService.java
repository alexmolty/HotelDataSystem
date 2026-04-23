package hotel.interfaces;

import hotel.model.Booking;
import hotel.model.Guest;
import hotel.model.Room;
import hotel.model.RoomType;

import java.time.LocalDate;
import java.util.List;

public interface IHotelManagingService {
    boolean registerRoomType(RoomType roomType);

    boolean registerRoom(Room room);

    boolean registerGuest(Guest guest);

    boolean isRoomAvailable(Room room, LocalDate start, LocalDate end);

    boolean createBooking(Guest guest, Room room, LocalDate checkIn, LocalDate checkOut);

    boolean createBooking(Booking booking);

    boolean removeBooking(int bookingId);

    boolean removeRoom(Room room);

    boolean removeRoomType(String roomTypeName);

    boolean removeGuest(int guestId);

    List<Booking> getBookingsStartOn(LocalDate checkInDate);

    List<Booking> getBookingsByGuestsId(int guestId);
}
