package hotel.app.part4_dataGeneration;

import hotel.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomHotelDataGenerator {
    private final int MIN_ROOMS_NUMBER = 10;
    private final int MAX_ROOMS_NUMBER = 20;
    private final int GUESTS_COUNT = 10;
    private final int BOOKINGS_COUNT = 20;
    private final int FIRST_ROOM_NUMBER = 100;
    private final int GUESTS_FIRST_ID = 10000;
    private final int PERIOD_OF_RANDOM_BOOKINGS = 365;
    private final int MIN_DAYS_TO_CHECKOUT = 1;
    private final int MAX_DAYS_TO_CHECKOUT = 10;

    private final Random rand = new Random();

    public List<RoomType> getRoomTypes() {
        RoomType standard = new RoomType(RoomTypeName.STANDARD, 100.0, 2);
        RoomType deluxe = new RoomType(RoomTypeName.DELUXE, 200.0, 3);
        RoomType royal = new RoomType(RoomTypeName.ROYAL, 300.0, 4);
        return List.of(standard, deluxe, royal);
    }

    public List<Room> getRooms() {
        List<RoomType> roomTypes = getRoomTypes();
        List<Room> rooms = new ArrayList<>();
        int count = rand.nextInt(MIN_ROOMS_NUMBER, MAX_ROOMS_NUMBER + 1);
        for (int i = 0; i < count; i++) {
            RoomType randomType = roomTypes.get(rand.nextInt(roomTypes.size()));
            rooms.add(new Room(FIRST_ROOM_NUMBER + i, randomType, rand.nextBoolean()));
        }
        return rooms;
    }

    public List<Guest> getGuests() {
        List<Guest> guests = new ArrayList<>();
        for (int i = 1; i <= GUESTS_COUNT; i++) {
            String guestName = "guest" + i;
            String guestEmail = guestName + "@hotel.com";
            String guestPassword = "pass" + i;
            guests.add(new Guest(GUESTS_FIRST_ID + i, guestName, guestEmail, guestPassword));
        }
        return guests;
    }

    public List<Booking> getBookings() {
        List<Booking> bookings = new ArrayList<>();
        List<Guest> guests = getGuests();
        List<Room> rooms = getRooms();
        for (int i = 0; i < BOOKINGS_COUNT; i++) {
            Guest randGuest = guests.get(rand.nextInt(guests.size()));
            Room randRoom = rooms.get(rand.nextInt(rooms.size()));

            LocalDate checkIn = LocalDate.now().plusDays(rand.nextInt(PERIOD_OF_RANDOM_BOOKINGS));
            LocalDate checkOut = checkIn.plusDays(rand.nextInt(MIN_DAYS_TO_CHECKOUT, MAX_DAYS_TO_CHECKOUT + 1));
            bookings.add(new Booking(randGuest, randRoom, checkIn, checkOut));
        }
        return bookings;
    }
}
