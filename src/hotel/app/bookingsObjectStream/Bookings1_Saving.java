package hotel.app.bookingsObjectStream;

import hotel.model.*;
import hotel.service.FileService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Bookings1_Saving {
    public static void main(String[] args) {
        FileService fileService = new FileService();
        List<Booking> bookings = new ArrayList<>();

        Guest g1 = new Guest(351031, "Hector Salamanca", "deskbell@gmail.com", "1234");
        RoomType standard = new RoomType(RoomTypeName.STANDARD, 150.0, 2);
        Room r1 = new Room(101, standard);
        Booking b1 = new Booking(g1, r1, LocalDate.of(2026, 12, 31), LocalDate.of(2027, 1, 4));

        Guest g2 = new Guest(461312, "Gustavo Fring", "chickenman@gmail.com", "5678");
        RoomType deluxe = new RoomType(RoomTypeName.DELUXE, 200.0, 3);
        Room r2 = new Room(102, deluxe);
        Booking b2 = new Booking(g2, r2, LocalDate.of(2026, 11, 26), LocalDate.of(2026, 11, 29));

        bookings.add(b1);
        bookings.add(b2);
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
        String filename = "bookings.data";
        fileService.saveBookings(bookings, filename);
    }
}
