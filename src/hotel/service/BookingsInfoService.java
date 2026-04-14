package hotel.service;

import hotel.interfaces.BookingsServiceInterface;
import hotel.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookingsInfoService implements BookingsServiceInterface {
    public int getNumberOfBookings(List<Booking> bookings) {
        if (bookings == null || bookings.isEmpty()) {
            return 0;
        }
        return bookings.size();
    }

    public double getTotalIncome(List<Booking> bookings) {
        if (bookings == null || bookings.isEmpty()) {
            return 0;
        }
        return bookings.stream()
                .mapToDouble(Booking::getTotalPrice)
                .sum();
    }

    public double getAverageBookingPrice(List<Booking> bookings) {
        if (bookings == null || bookings.isEmpty()) {
            return 0;
        }
        int count = getNumberOfBookings(bookings);
        return count == 0 ? 0 : getTotalIncome(bookings) / count;
    }

    public String getMostPopularRoomTypes(List<Booking> bookings) {
        if (bookings == null || bookings.isEmpty()) {
            return "No types found> ";
        }
        Map<RoomTypeName, Long> typeCounts = bookings.stream()
                .collect(
                        Collectors.groupingBy(
                                b -> b.getRoom().getType().getRoomTypeName(),
                                Collectors.counting()
                        )
                );
        long maxCountOfBookings = typeCounts.values().stream().max(Long::compare).orElse(0L);

        List<String> winnerNames = new ArrayList<>();
        for (var entry : typeCounts.entrySet()) {
            if (entry.getValue() == maxCountOfBookings) {
                String s = entry.getKey().name();
                winnerNames.add(s.charAt(0) + s.substring(1).toLowerCase());
            }
        }
        return String.join(", ", winnerNames);
    }
}
