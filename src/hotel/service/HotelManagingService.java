package hotel.service;

import hotel.interfaces.IHotelManagingService;
import hotel.model.*;

import java.time.LocalDate;

import java.util.List;
import java.util.Objects;

public class HotelManagingService implements IHotelManagingService {
    private final Hotel hotel;

    public HotelManagingService(Hotel hotel) {
        this.hotel = Objects.requireNonNull(hotel);
    }

    @Override
    public boolean registerRoomType(RoomType roomType) {
        Objects.requireNonNull(roomType, "RoomType cannot be null");
        String name = hotel.normalizeRoomTypeName(roomType.getRoomTypeName());
        if (hotel.getRoomTypes().containsKey(name)) {
            System.err.println("Room type already exists in this hotel.");
            return false;
        }
        hotel.addRoomType(roomType);
        return true;
    }

    @Override
    public boolean registerRoom(Room room) {
        Objects.requireNonNull(room, "Room cannot be null");
        String name = hotel.normalizeRoomTypeName(room.getType().getRoomTypeName());
        Integer roomNumber = room.getRoomNumber();
        if (!hotel.getRoomTypes().containsKey(name)) {
            System.err.println("Type does not exist in this hotel.");
            return false;
        }
        if (hotel.getRooms().containsKey(roomNumber)) {
            System.err.println("This room already exists in this hotel.");
            return false;
        }

        hotel.addRoom(room);
        return true;
    }

    @Override
    public boolean registerGuest(Guest guest) {
        Objects.requireNonNull(guest, "Guest cannot be null");
        if (hotel.getGuests().containsKey(guest.getId())) {
            System.err.println("This guest already exists in this hotel.");
            return false;
        }
        hotel.addGuest(guest);
        return true;
    }

    @Override
    public boolean isRoomAvailable(Room room, LocalDate start, LocalDate end) {
        Objects.requireNonNull(room, "Room cannot be null");
        Objects.requireNonNull(start, "Start date cannot be null");
        Objects.requireNonNull(end, "End date cannot be null");
        return hotel.getBookings().values().stream()
                .filter(b -> b.getRoom().equals(room))
                .noneMatch(b -> b.overlaps(start, end));
    }

    @Override
    public boolean createBooking(Guest guest, Room room, LocalDate checkIn, LocalDate checkOut) {
        Objects.requireNonNull(hotel, "Hotel cannot be null");
        Objects.requireNonNull(guest, "Guest cannot be null");
        Objects.requireNonNull(room, "Room cannot be null");
        Objects.requireNonNull(checkIn, "Start date cannot be null");
        Objects.requireNonNull(checkOut, "End date cannot be null");
        if (!hotel.getGuests().containsKey(guest.getId())) {
            System.err.println("Guest with ID " + guest.getId() + " not found in this hotel.");
            return false;
        }
        if (checkOut.isBefore(checkIn)) {
            System.err.println("Check out date cannot be before check In.");
            return false;
        }
        if (!hotel.getRooms().containsKey(room.getRoomNumber())) {
            System.err.println("Rooms not found in " + hotel.getHotelName());
            return false;
        }
        if (!isRoomAvailable(room, checkIn, checkOut)) {
            System.err.println("Room " + room.getRoomNumber() + " is occupied for selected dates.");
            return false;
        }
        hotel.addBooking(new Booking(guest, room, checkIn, checkOut));
        return true;
    }

    @Override
    public boolean createBooking(Booking booking) {
        Objects.requireNonNull(booking, "Booking cannot be null");
        if (hotel.getBookings().containsKey(booking.getBookingId())) {
            System.err.println("Booking with ID " + booking.getBookingId() + " already exist in this hotel.");
            return false;
        }
        if (!hotel.getGuests().containsKey(booking.getGuest().getId())) {
            System.err.println("Guest with ID " + booking.getGuest().getId() + " not found in this hotel.");
            return false;
        }
        if (!hotel.getRooms().containsKey(booking.getRoom().getRoomNumber())) {
            System.err.println("Rooms not found in " + hotel.getHotelName());
            return false;
        }
        if (!isRoomAvailable(booking.getRoom(), booking.getCheckIn(), booking.getCheckOut())) {
            System.err.println("Room " + booking.getRoom().getRoomNumber() + " is occupied for selected dates.");
            return false;
        }
        Booking.synchronizedCounter(booking.getBookingId());
        hotel.addBooking(booking);
        return true;
    }

    @Override
    public boolean removeBooking(int bookingId) {
        Booking bookingDeleted = hotel.removeBooking(bookingId);
        return bookingDeleted != null;
    }

    @Override
    public boolean removeRoom(Room room) {
        Objects.requireNonNull(room, "Room cannot be null");
        if (!hotel.getRooms().containsKey(room.getRoomNumber())) {
            System.err.println("Room " + room.getRoomNumber() + " does not exist in this hotel.");
            return false;
        }
        boolean hasRelatedBookings = hotel.getBookings().values().stream()
                .anyMatch(b -> b.getRoom().equals(room));
        if (hasRelatedBookings) {
            System.err.println("Cannot remove room " + room.getRoomNumber() + " because of related bookings.");
            return false;
        }
        Room roomToDelete = hotel.removeRoom(room);
        return roomToDelete != null;
    }

    @Override
    public boolean removeRoomType(String roomTypeName) {
        if (roomTypeName == null || roomTypeName.isEmpty()) {
            System.err.println("Room type name cannot be null or empty.");
            return false;
        }
        boolean typeIsUsedByRooms = hotel.getRooms().values().stream()
                .map(Room::getType)
                .anyMatch(t -> t.equals(roomTypeName));
        if (typeIsUsedByRooms) {
            System.err.println("Cannot remove room type " + roomTypeName);
            return false;
        }
        RoomType roomTypeToDelete = hotel.removeRoomType(roomTypeName);
        return roomTypeToDelete != null;
    }

    @Override
    public boolean removeGuest(int guestId) {
        boolean guestHaveBookings = hotel.getBookings().values().stream()
                .map(Booking::getGuest)
                .anyMatch(g -> g.getId() == guestId);
        if (guestHaveBookings) {
            System.err.println("Cannot remove guest " + guestId + " because of related bookings.");
            return false;
        }
        Guest guestToDelete = hotel.removeGuest(guestId);
        return guestToDelete != null;
    }

    @Override
    public List<Booking> getBookingsStartOn(LocalDate checkInDate) {
        return hotel.getBookingsCheckInDate().get(checkInDate);
    }

    @Override
    public List<Booking> getBookingsByGuestsId(int guestId) {
        return hotel.getBookings().values().stream()
                .filter(booking -> booking.getGuest().getId() == guestId)
                .toList();
    }

    // TODO changeCheckIn changeCheckOut
}


