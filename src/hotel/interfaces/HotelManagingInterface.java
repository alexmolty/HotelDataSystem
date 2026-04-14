package hotel.interfaces;

import hotel.model.Guest;
import hotel.model.Hotel;
import hotel.model.Room;
import hotel.model.RoomType;

import java.time.LocalDate;

public interface HotelManagingInterface {
    public boolean isRoomAvailable(Hotel hotel, Room room, LocalDate start, LocalDate end);
    public boolean createBooking(Hotel hotel, Guest guest, Room room, LocalDate checkIn, LocalDate checkOut);
    public boolean registerRoomType(Hotel hotel, RoomType roomType);
    public boolean registerRoom(Hotel hotel, Room room);


}
