package hotel.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hotel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String hotelName;
    private List<RoomType> roomTypes;
    private List<Room> rooms;
    private List<Guest> guests;
    private List<Booking> bookings;

    public Hotel(String hotelName) {
        if (hotelName == null || hotelName.isBlank()) {
            throw new IllegalArgumentException("Hotel name cannot be null or blank");
        }

        this.hotelName = hotelName;
        System.out.println("Welcome to " + hotelName + "!");
        this.roomTypes = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.guests = new ArrayList<>();
        this.bookings = new ArrayList<>();

    }

    public void addRoomType(RoomType roomType) {
        roomTypes.add(roomType);
    }

    public void addRoom(Room newRoom) {
        rooms.add(newRoom);
    }


    public String getHotelName() {
        return hotelName;
    }

    public List<RoomType> getRoomTypes() {
        return List.copyOf(roomTypes);
    }

    public List<Room> getRooms() {
        return (rooms == null) ? List.of() : List.copyOf(rooms);
    }

    public List<Booking> getBookings() {
        return List.copyOf(bookings);
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void addBooking(Booking newBooking) {
        if (newBooking == null) throw new IllegalArgumentException();
        this.bookings.add(newBooking);
    }

    public boolean removeBooking(Booking booking) {
        if (booking == null) throw new IllegalArgumentException();
        return this.bookings.remove(booking);
    }

}
