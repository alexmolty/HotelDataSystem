package hotel.app.part5_analytics;

import hotel.app.part4_dataGeneration.RandomHotelDataGenerator;
import hotel.model.Booking;
import hotel.model.Room;
import hotel.service.FileService;
import hotel.service.HotelService;

import java.util.List;

public class AnalyticsApp {
    public static void main(String[] args) {
        RandomHotelDataGenerator hotel = new RandomHotelDataGenerator();
        HotelService hotelService = new HotelService();
        FileService fileService = new FileService();

        List<Booking> bookingsToSave = hotel.getBookings();
        fileService.saveBookings(bookingsToSave, "bookings_generated.data");
        List<Booking> bookings = fileService.readBookings("bookings_generated.data");
        List<Room> rooms = hotel.getRooms();
        if (bookings == null || bookings.isEmpty()) {
            System.err.println("No bookings found");
            return;
        }

        System.out.println("Total number of bookings: " + hotelService.getNumberOfBookings(bookings));
        System.out.printf("Total income: %.2f $%n", +hotelService.getTotalIncome(bookings));
        System.out.printf("Average booking price: %.2f $%n", hotelService.getAverageBookingPrice(bookings));
        System.out.println("Most popular room type: " + hotelService.getMostPopularRoomTypes(bookings));
        System.out.println("Available rooms: " + hotelService.getAvailableRooms(rooms));
        System.out.println("Occupied rooms: " + hotelService.getOccupiedRooms(rooms));
        System.out.println("List of all bookings: ");
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }
}
