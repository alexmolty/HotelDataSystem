package hotel.service;

import hotel.interfaces.IHotelInfoService;
import hotel.model.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class HotelInfoService implements IHotelInfoService {
    private final Hotel hotel;

    public HotelInfoService(Hotel hotel) {
        this.hotel = Objects.requireNonNull(hotel);
    }

    @Override
    public int getNumberOfBookings() {
        if (hotel.getBookings().isEmpty()) {
            return 0;
        }
        return hotel.getBookings().size();
    }

    @Override
    public double getTotalIncome() {
        if (hotel.getBookings().isEmpty()) {
            return 0;
        }
        return hotel.getBookings().values().stream()
                .mapToDouble(Booking::getTotalPrice)
                .sum();
    }

    @Override
    public double getAverageBookingPrice() {
        if (hotel.getBookings().isEmpty()) {
            return 0;
        }
        int count = getNumberOfBookings();
        return count == 0 ? 0 : getTotalIncome() / count;
    }

    @Override
    public String getMostPopularRoomTypes() {
        if (hotel.getBookings().isEmpty()) {
            return "No types found";
        }
        Map<String, Long> typeCounts = hotel.getBookings().values().stream()
                .collect(
                        Collectors.groupingBy(
                                b -> b.getRoom().getType().getRoomTypeName(),
                                Collectors.counting()
                        )
                );
        long maxCountOfBookings = typeCounts.values().stream().max(Long::compare).orElse(0L);

        return typeCounts.entrySet().stream()
                .filter(entry -> entry.getValue() == maxCountOfBookings)
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(", "));
    }

    @Override
    public List<Room> getAvailableRoomsForDate(LocalDate date) {
        if (hotel.getRooms().isEmpty()) {
            return List.of();
        }
        return hotel.getRooms().values().stream()
                .filter(room -> isAvailable(room, date))
                .toList();
    }

    @Override
    public long getAvailableRoomsForDateCount(LocalDate date) {
        return getAvailableRoomsForDate(date).size();
    }

    @Override
    public boolean isAvailable(Room room, LocalDate date) {
        Objects.requireNonNull(room, "Room cannot be null");
        Objects.requireNonNull(date, "Date cannot be null");
        return hotel.getBookings().values().stream()
                .noneMatch(booking -> booking.getRoom().equals(room) && booking.isActiveOn(date));
    }

    @Override
    public List<String> getMostPopularRoomTypesForAgeRange(Map<Integer, Booking> bookings, int minAge, int maxAge) {
        Objects.requireNonNull(bookings, "Bookings cannot be null");
        if (minAge > maxAge) {
            System.err.println("Minimum age cannot be greater than maximum age");
            return List.of();
        }

        Map<String, Long> counts = bookings.values().stream()
                .filter(
                        b -> {
                            int age = b.getGuest().getAge();
                            return age >= minAge && age <= maxAge;
                        }
                )
                .collect(Collectors.groupingBy(
                                b -> b.getRoom().getType().getRoomTypeName(),
                                Collectors.counting()
                        )
                );
        if (counts.isEmpty()) {
            return List.of();
        }
        long max = Collections.max(counts.values());
        return counts.entrySet().stream()
                .filter(e -> e.getValue() == max)
                .map(Map.Entry::getKey)
                .toList();
    }

    @Override
    public List<String> getMostPopularRoomTypesForAgeRange(int minAge, int maxAge) {
        return getMostPopularRoomTypesForAgeRange(hotel.getBookings(), minAge, maxAge);
    }
}
