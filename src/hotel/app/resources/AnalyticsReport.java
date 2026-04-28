package hotel.app.resources;

import hotel.model.*;
import hotel.service.*;

public class AnalyticsReport {

    private final Hotel hotel;
    private final HotelInfoService infoService;

    public AnalyticsReport(Hotel hotel, HotelInfoService infoService) {
        this.hotel = hotel;
        this.infoService = infoService;
    }
    public void showFullReport() {
        System.out.println("Hotel Name: " + hotel.getHotelName());
        System.out.println("Total number of bookings: " + infoService.getNumberOfBookings());
        System.out.printf("Total income: %.2f $%n", infoService.getTotalIncome());
        System.out.printf("Average booking price: %.2f $%n", infoService.getAverageBookingPrice());
        System.out.println("Most popular room type: " + infoService.getMostPopularRoomTypes());
    }
    public void showAllRooms() {
        System.out.println("--- List of all rooms ---");
        hotel.getRooms().values().forEach(System.out::println);
        System.out.println(" ============================= \n");
    }
    public void showAllBookings() {
        System.out.println("--- List of all bookings ---");
        hotel.getBookings().values().forEach(System.out::println);
        System.out.println(" ============================= \n");
    }
    public void showAllGuests() {
        System.out.println("--- List of all guests ---");
        hotel.getGuests().values().forEach(System.out::println);
        System.out.println(" ============================= \n");
    }
    public void showAllRoomTypes() {
        System.out.println("--- List of all room types --");
        hotel.getRoomTypes().values().forEach(System.out::println);
        System.out.println(" ============================= \n");
    }
}
