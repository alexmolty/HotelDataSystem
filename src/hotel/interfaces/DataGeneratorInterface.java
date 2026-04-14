package hotel.interfaces;

import hotel.model.*;

import java.util.List;

public interface DataGeneratorInterface {
    List<RoomType> generateRoomTypes();
    List<Room> generateRooms(List<RoomType> roomTypes);
    List<Guest> generateGuests();
    void generateBookings();
}