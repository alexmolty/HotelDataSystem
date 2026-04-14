package hotel.interfaces;

import hotel.model.*;

import java.util.List;

public interface BookingsServiceInterface {
    public int getNumberOfBookings(List<Booking> bookings);

    public double getTotalIncome(List<Booking> bookings);

    public double getAverageBookingPrice(List<Booking> bookings);

    public String getMostPopularRoomTypes(List<Booking> bookings);
}
