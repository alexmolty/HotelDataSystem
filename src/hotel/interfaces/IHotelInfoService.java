package hotel.interfaces;

import hotel.model.Booking;
import hotel.model.Hotel;
import hotel.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface IHotelInfoService {
    int getNumberOfBookings(List<Booking> bookings);

    double getTotalIncome(List<Booking> bookings);

    double getAverageBookingPrice(List<Booking> bookings);

    String getMostPopularRoomTypes(List<Booking> bookings);

    long getAvailableRoomsCount(Hotel hotel, LocalDate date);

    boolean isAvailable(Hotel hotel, Room room, LocalDate date);
}
