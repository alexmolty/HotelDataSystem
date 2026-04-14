package hotel.app.roomTypeDataStream;

import hotel.model.RoomType;
import hotel.service.FileService;

import java.util.List;

public class RoomType2_Reading {
    public static void main(String[] args) {
        FileService fileService = new FileService();
        String filename = "roomtypes.data";

        List<RoomType> roomTypes = fileService.readRoomTypes(filename);

        for (RoomType type : roomTypes) {
            System.out.println(type);
        }
    }
}
