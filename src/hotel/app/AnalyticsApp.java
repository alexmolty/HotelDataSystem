package hotel.app;

import hotel.generator.*;
import hotel.model.*;
import hotel.service.*;

import java.util.List;

public class AnalyticsApp {
    public static void main(String[] args) {
        Hotel hotel = new Hotel("Pacific Resort");
        HotelManagingService manager = new HotelManagingService();
        HotelInfoService hotelInfoService = new HotelInfoService();
        FileService fileService = new FileService();

        RandomHotelDataGenerator generator = new RandomHotelDataGenerator(hotel, manager);

        System.out.println();
        List<Booking> bookingsToSave = hotel.getBookings();
        fileService.saveBookings(bookingsToSave, "bookings_generated.data");
        List<Booking> bookings = fileService.readBookings("bookings_generated.data");
        fileService.saveRooms(hotel.getRooms(), "rooms_generated.data");
        List<Room> rooms = fileService.readRooms("rooms_generated.data");
        if (bookings == null || bookings.isEmpty()) {
            System.err.println("No bookings found");
            return;
        }
        if (rooms == null || rooms.isEmpty()) {
            System.err.println("No rooms found");
            return;
        }

        System.out.println("\n ====== HOTEL ANALYTICS ====== \n");
        System.out.println("Total number of bookings: " + hotelInfoService.getNumberOfBookings(bookings));
        System.out.printf("Total income: %.2f $%n", +hotelInfoService.getTotalIncome(bookings));
        System.out.printf("Average booking price: %.2f $%n", hotelInfoService.getAverageBookingPrice(bookings));
        System.out.println("Most popular room type: " + hotelInfoService.getMostPopularRoomTypes(bookings));
        System.out.println("List of all bookings: ");
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }
}
