package hotel.service;

import hotel.model.Booking;
import hotel.model.Room;
import hotel.model.RoomTypeName;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HotelService {
    public int getNumberOfBookings(List<Booking> bookings) {
        return bookings.size();
    }

    public double getTotalIncome(List<Booking> bookings) {
        return bookings.stream()
                .mapToDouble(b -> {
                            long nights = ChronoUnit.DAYS.between(b.getCheckIn(), b.getCheckOut());
                            return b.getRoom().getType().getPricePerNight() * nights;
                        }
                ).sum();
    }

    public double getAverageBookingPrice(List<Booking> bookings) {
        int count = getNumberOfBookings(bookings);
        return count == 0 ? 0 : getTotalIncome(bookings) / getNumberOfBookings(bookings);
    }

    public String getMostPopularRoomTypes(List<Booking> bookings) {
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

    public List<Room> getAllRooms(List<Booking> bookings) {
        return bookings.stream()
                .map(Booking::getRoom)
                .distinct()
                .toList();
    }

    public Map<Boolean, Long> getAvailableMapOfRooms(List<Room> rooms) {
        return rooms.stream()
                .collect(
                        Collectors.groupingBy(
                                Room::isAvailable,
                                Collectors.counting()
                        )
                );
    }

    public long getAvailableRooms(List<Room> rooms) {
        return getAvailableMapOfRooms(rooms).getOrDefault(true, 0L);
    }

    public long getOccupiedRooms(List<Room> rooms) {
        return getAvailableMapOfRooms(rooms).getOrDefault(false, 0L);
    }
}
