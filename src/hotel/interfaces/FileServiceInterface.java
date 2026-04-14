package hotel.interfaces;

import hotel.model.*;

import java.util.List;

public interface FileServiceInterface {
    public void saveRoomTypes(List<RoomType> typesRooms, String filename);

    public List<RoomType> readRoomTypes(String filename);

    public void saveBookings(List<Booking> bookings, String filename);

    public List<Booking> readBookings(String filename);

    public  void saveRooms(List<Room> rooms, String filename);

    public List<Room> readRooms(String filename);
}
