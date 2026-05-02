package hotel.app.resources;

import cli.InputOutput;
import hotel.interfaces.IFileService;
import hotel.interfaces.IHotelInfoService;
import hotel.interfaces.IHotelManagingService;

public class HotelApplContext {
    private final InputOutput inOut;
    private final IHotelInfoService hotelInfoService;
    private final IFileService fileService;
    private final IHotelManagingService hotelManagingService;

    private final String bookingsFile;
    private final String roomsFile;
    private final String guestsFile;
    private final String roomTypesFile;
    private final String dateFormat;

    public HotelApplContext(InputOutput inOut, IHotelInfoService hotelInfoService, IFileService fileService, IHotelManagingService hotelManagingService, String bookingsFile, String roomsFile, String guestsFile, String roomTypesFile, String dateFormat) {
        this.inOut = inOut;
        this.hotelInfoService = hotelInfoService;
        this.fileService = fileService;
        this.hotelManagingService = hotelManagingService;
        this.bookingsFile = bookingsFile;
        this.roomsFile = roomsFile;
        this.guestsFile = guestsFile;
        this.roomTypesFile = roomTypesFile;
        this.dateFormat = dateFormat;
    }
    public void saveAll() {
            fileService.saveRoomTypes(roomTypesFile);
            fileService.saveRooms(roomsFile);
            fileService.saveBookings(bookingsFile);
            fileService.saveGuests(guestsFile);
    }

    public InputOutput getInOut() {
        return inOut;
    }

    public IHotelInfoService getHotelInfoService() {
        return hotelInfoService;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public IHotelManagingService getHotelManagingService() {
        return hotelManagingService;
    }
}
