package hotel.app;

import cli.*;
import hotel.app.resources.AnalyticsReport;
import hotel.generator.RandomHotelDataGenerator;
import hotel.model.*;
import hotel.service.*;

import java.nio.file.Files;
import java.nio.file.Path;

public class HotelAppl {
    // Названия файлов для хранения базы данных
    private static final String BOOKINGS_FILE = "bookings.data";
    private static final String ROOMS_FILE = "rooms.data";
    private static final String GUESTS_FILE = "guests.data";
    private static final String ROOM_TYPES_FILE = "room_types.data";

    public static void main(String[] args) {
        Hotel hotel = new Hotel("Pacific Resort");
        HotelManagingService manager = new HotelManagingService(hotel);
        HotelInfoService infoService = new HotelInfoService(hotel);
        FileService fileService = new FileService(hotel);

        if (!Files.exists(Path.of(ROOMS_FILE))) {
            System.out.println(">>> База данных не найдена. Запуск генерации новых данных...");
            new RandomHotelDataGenerator(hotel, manager);
            saveAllData(hotel, fileService);
        } else {
            System.out.println(">>> Обнаружены файлы базы данных. Загрузка...");
            loadAllData(hotel, fileService);

        }
        AnalyticsReport analytics = new AnalyticsReport(hotel, infoService);
        analytics.showAllRoomTypes();
        analytics.showAllGuests();
        analytics.showAllRooms();
        analytics.showAllBookings();
        analytics.showFullReport();


        // TODO: CLI


    }

    private static void loadAllData(Hotel hotel, FileService fs) {
        fs.readRoomTypes(ROOM_TYPES_FILE).values().forEach(hotel::addRoomType);
        fs.readRooms(ROOMS_FILE).values().forEach(hotel::addRoom);
        fs.readGuests(GUESTS_FILE).values().forEach(hotel::addGuest);
        fs.readBookings(BOOKINGS_FILE).values().forEach(hotel::addBooking);

        System.out.println(">>> Все данные успешно восстановлены в памяти.");
    }

    private static void saveAllData(Hotel hotel, FileService fs) {
        System.out.println(">>> Сохранение данных в файлы...");
        fs.saveRoomTypes(ROOM_TYPES_FILE);
        fs.saveRooms(ROOMS_FILE);
        fs.saveGuests(GUESTS_FILE);
        fs.saveBookings(BOOKINGS_FILE);
        System.out.println(">>> Данные сохранены.");
    }
}