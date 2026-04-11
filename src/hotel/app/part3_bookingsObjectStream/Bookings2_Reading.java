package hotel.app.part3_bookingsObjectStream;

import hotel.model.Booking;
import hotel.service.FileService;

import java.util.List;

public class Bookings2_Reading {
    public static void main(String[] args) {
        String filename = "bookings.data";
        FileService fileService = new FileService();
        List<Booking> bookings = fileService.readBookings(filename);
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }
}
