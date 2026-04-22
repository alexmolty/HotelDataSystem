package hotel.service;

import hotel.interfaces.IHotelInfoService;
import hotel.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class HotelInfoService implements IHotelInfoService {
    @Override
    public int getNumberOfBookings(List<Booking> bookings) {
        if (bookings == null || bookings.isEmpty()) {
            return 0;
        }
        return bookings.size();
    }

    @Override
    public double getTotalIncome(List<Booking> bookings) {
        if (bookings == null || bookings.isEmpty()) {
            return 0;
        }
        return bookings.stream()
                .mapToDouble(Booking::getTotalPrice)
                .sum();
    }

    @Override
    public double getAverageBookingPrice(List<Booking> bookings) {
        if (bookings == null || bookings.isEmpty()) {
            return 0;
        }
        int count = getNumberOfBookings(bookings);
        return count == 0 ? 0 : getTotalIncome(bookings) / count;
    }

    @Override
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

    @Override
    public long getAvailableRoomsCount(Hotel hotel, LocalDate date) {
        Objects.requireNonNull(hotel, "Hotel cannot be null");
        if (hotel.getRooms().isEmpty()) {
            return 0;
        }
        return hotel.getRooms().stream().filter(room -> isAvailable(hotel, room, date)).count();
    }

    @Override
    public boolean isAvailable(Hotel hotel, Room room, LocalDate date) {
        Objects.requireNonNull(hotel, "Hotel cannot be null");
        Objects.requireNonNull(room, "Room cannot be null");
        Objects.requireNonNull(date, "Date cannot be null");
        return hotel.getBookings().stream()
                .noneMatch(booking -> booking.getRoom().equals(room) && booking.isActiveOn(date));
    }
}
