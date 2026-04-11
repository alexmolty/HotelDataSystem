package hotel.app.part2_roomTypeDataStream;

import hotel.model.RoomType;
import hotel.model.RoomTypeName;
import hotel.service.FileService;

import java.util.List;

public class RoomType1_Saving {
    public static void main(String[] args) {
        FileService fileService = new FileService();

        RoomType standard = new RoomType(RoomTypeName.STANDARD, 150.0, 2);
        RoomType superior = new RoomType(RoomTypeName.SUPERIOR, 170.0, 2);
        RoomType deluxe = new RoomType(RoomTypeName.DELUXE, 200.0, 3);
        RoomType royal = new RoomType(RoomTypeName.ROYAL, 300.0, 4);
        List<RoomType> typesRooms = List.of(standard, superior, deluxe, royal);

        String filename = "roomtypes.data";

        fileService.saveRoomTypes(typesRooms, filename);
    }
}
