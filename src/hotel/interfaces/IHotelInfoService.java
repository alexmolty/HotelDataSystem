package hotel.interfaces;

import hotel.model.Booking;
import hotel.model.Guest;
import hotel.model.Room;
import hotel.model.RoomType;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IHotelInfoService {
    // Find one
    Room findRoomByNumber(int roomId);

    Guest findGuestById(int guestId);

    RoomType findRoomTypeByName(String roomTypeName);

    Booking findBookingById(int bookingId);

    // Get many
    Map<String, RoomType> getRoomTypes();

    Map<Integer, Room> getRooms();

    Map<Integer, Booking> getBookings();

    Map<Integer, Guest> getGuests();

    // Availability
    boolean isRoomAvailableForDate(Room room, LocalDate date);

    boolean isRoomAvailableForDates(Room room, LocalDate checkInDate, LocalDate checkOutDate);

    List<Room> getAvailableRoomsForDate(LocalDate date);

    List<Room> getAvailableRoomsForDates(LocalDate checkInDate, LocalDate checkOutDate);

    long getAvailableRoomsForDateCount(LocalDate date);

    // Statistics
    int getNumberOfBookings();

    double getTotalIncome();

    double getAverageBookingPrice();

    String getMostPopularRoomTypes();

    List<String> getMostPopularRoomTypesForAgeRange(Map<Integer, Booking> bookings, int minAge, int maxAge);

    List<String> getMostPopularRoomTypesForAgeRange(int minAge, int maxAge);

    List<Booking> getBookingsStartOn(LocalDate checkInDate);

    List<Booking> getBookingsByGuestsId(int guestId);
}
