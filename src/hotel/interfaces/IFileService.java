package hotel.interfaces;

import hotel.model.Booking;
import hotel.model.Guest;
import hotel.model.Room;
import hotel.model.RoomType;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IFileService {
    void saveGuests(String filename);

    void saveRoomTypes(String filename);

    void saveBookings(String filename);

    void saveRooms(String filename);

    Map<Integer, Guest> readGuests(String filename);

    Map<String, RoomType> readRoomTypes(String filename);

    Map<Integer, Booking> readBookings(String filename);

    Map<Integer, Room> readRooms(String filename);
}
