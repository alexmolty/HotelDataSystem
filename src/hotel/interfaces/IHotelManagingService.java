package hotel.interfaces;

import hotel.model.Booking;
import hotel.model.Guest;
import hotel.model.Room;
import hotel.model.RoomType;

import java.time.LocalDate;

public interface IHotelManagingService {
    boolean registerRoomType(RoomType roomType);

    boolean registerRoom(Room room);

    void registerGuest(Guest guest);

    boolean isRoomAvailableForDates(Room room, LocalDate start, LocalDate end);

    Booking createBooking(Guest guest, Room room, LocalDate checkIn, LocalDate checkOut);

    Booking createBooking(Booking booking);

    boolean removeBooking(int bookingId);

    boolean removeRoom(int roomNumber);

    boolean removeRoomType(String roomTypeName);

    boolean removeGuest(int guestId);
}
