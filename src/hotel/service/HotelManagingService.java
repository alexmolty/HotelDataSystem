package hotel.service;

import hotel.interfaces.HotelManagingInterface;
import hotel.model.*;

import java.time.LocalDate;

import java.util.Objects;

public class HotelManagingService implements HotelManagingInterface {
    public boolean registerRoomType(Hotel hotel, RoomType roomType) {
        Objects.requireNonNull(hotel, "Hotel cannot be null");
        Objects.requireNonNull(roomType, "RoomType cannot be null");

        if (hotel.getRoomTypes().contains(roomType)) {
            System.err.println("Room type already exists in this hotel.");
            return false;
        }
        hotel.addRoomType(roomType);
        return true;
    }

    public boolean registerRoom(Hotel hotel, Room room) {
        Objects.requireNonNull(hotel, "Hotel cannot be null");
        Objects.requireNonNull(room, "Room cannot be null");

        if (!hotel.getRoomTypes().contains(room.getType())) {
            System.err.println("Type does not exist in this hotel.");
            return false;
        }
        if (hotel.getRooms().contains(room)) {
            System.err.println("This room already exists in this hotel.");
            return false;
        }

        hotel.addRoom(room);
        return true;
    }

    public boolean isRoomAvailable(Hotel hotel, Room room, LocalDate start, LocalDate end) {
        return hotel.getBookings().stream()
                .filter(b -> b.getRoom().equals(room))
                .noneMatch(b -> start.isBefore(b.getCheckOut()) && end.isAfter(b.getCheckIn()));
    }

    public boolean createBooking(Hotel hotel, Guest guest, Room room, LocalDate checkIn, LocalDate checkOut) {
        Objects.requireNonNull(hotel, "Hotel cannot be null");
        Objects.requireNonNull(guest, "Guest cannot be null");
        Objects.requireNonNull(room, "Room cannot be null");
        Objects.requireNonNull(checkIn, "Start date cannot be null");
        Objects.requireNonNull(checkOut, "End date cannot be null");

        if (!hotel.getRooms().contains(room)) {
            System.err.println("Rooms not found in " + hotel.getHotelName());
            return false;
        }
        if (isRoomAvailable(hotel, room, checkIn, checkOut)) {
            hotel.addBooking(new Booking(guest, room, checkIn, checkOut));
            return true;
        }
        System.err.println("Room " + room.getRoomNumber() + " is occupied for selected dates.");
        return false;
    }


}
