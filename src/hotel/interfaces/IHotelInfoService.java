package hotel.interfaces;

import hotel.model.Booking;
import hotel.model.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IHotelInfoService {
    int getNumberOfBookings();

    double getTotalIncome();

    double getAverageBookingPrice();

    String getMostPopularRoomTypes();

    List<Room> getAvailableRoomsForDate(LocalDate date);

    long getAvailableRoomsForDateCount(LocalDate date);

    boolean isAvailable(Room room, LocalDate date);

    List<String> getMostPopularRoomTypesForAgeRange(Map<Integer, Booking> bookings, int minAge, int maxAge);

    List<String> getMostPopularRoomTypesForAgeRange(int minAge, int maxAge);
}
