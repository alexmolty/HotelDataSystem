package hotel.generator;

import hotel.interfaces.DataGeneratorInterface;
import hotel.model.*;
import hotel.service.HotelManagingService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomHotelDataGenerator implements DataGeneratorInterface {
    private final int MIN_ROOMS_NUMBER = 10;
    private final int MAX_ROOMS_NUMBER = 10;
    private final int GUESTS_COUNT = 10;
    private final int PERIOD_OF_RANDOM_BOOKINGS = 30;
    private final int BOOKINGS_COUNT = 15;
    private final int MIN_DAYS_TO_CHECKOUT = 1;
    private final int MAX_DAYS_TO_CHECKOUT = 10;

    private final int FIRST_ROOM_NUMBER = 100;
    private final int GUESTS_FIRST_ID = 10000;

    private final Random rand = new Random();

    private final Hotel hotel;
    private final HotelManagingService manager;
    private List<RoomType> roomTypes;
    private List<Room> rooms;
    private List<Guest> guests;

    public RandomHotelDataGenerator(Hotel hotel, HotelManagingService manager) {
        System.out.println("Starting random hotel data generator...");

        this.hotel = hotel;
        this.manager = manager;
        this.roomTypes = generateRoomTypes();
        this.rooms = generateRooms(this.roomTypes);
        this.guests = generateGuests();

        for (RoomType type : this.roomTypes) {
            this.manager.registerRoomType(this.hotel, type);
        }
        for (Room room : this.rooms) {
            this.manager.registerRoom(this.hotel,room);
        }

        generateBookings();
    }

    public List<RoomType> generateRoomTypes() {
        RoomType standard = new RoomType(RoomTypeName.STANDARD, 150.0, 2);
        RoomType superior = new RoomType(RoomTypeName.SUPERIOR, 170.0, 2);
        RoomType deluxe = new RoomType(RoomTypeName.DELUXE, 200.0, 3);
        RoomType royal = new RoomType(RoomTypeName.ROYAL, 300.0, 4);
        return List.of(standard, superior, deluxe, royal);
    }

    public List<Room> generateRooms(List<RoomType> roomTypes) {
        List<Room> rooms = new ArrayList<>();
        int count = rand.nextInt(MIN_ROOMS_NUMBER, MAX_ROOMS_NUMBER + 1);
        for (int i = 0; i < count; i++) {
            RoomType randomType = roomTypes.get(rand.nextInt(roomTypes.size()));
            rooms.add(new Room(FIRST_ROOM_NUMBER + i, randomType));
        }
        return rooms;
    }

    public List<Guest> generateGuests() {
        List<Guest> guests = new ArrayList<>();
        for (int i = 1; i <= GUESTS_COUNT; i++) {
            String guestName = "guest" + i;
            String guestEmail = guestName + "@hotel.com";
            String guestPassword = "pass" + i;
            guests.add(new Guest(GUESTS_FIRST_ID + i, guestName, guestEmail, guestPassword));
        }
        return guests;
    }

    public void generateBookings() {
        int attempts = 0;
        int maxAttempts = 1000;
        while (this.hotel.getBookings().size() < BOOKINGS_COUNT && attempts < maxAttempts) {
            attempts++;

            Guest randGuest = guests.get(rand.nextInt(guests.size()));
            Room randRoom = rooms.get(rand.nextInt(rooms.size()));
            LocalDate checkIn = LocalDate.now().plusDays(rand.nextInt(PERIOD_OF_RANDOM_BOOKINGS));
            LocalDate checkOut = checkIn.plusDays(rand.nextInt(MIN_DAYS_TO_CHECKOUT, MAX_DAYS_TO_CHECKOUT + 1));

            manager.createBooking(this.hotel, randGuest, randRoom, checkIn, checkOut);
        }
        if (attempts >= maxAttempts) {
            System.err.println("Can't create bookings after " + maxAttempts + " attempts");
            return;
        }
        System.out.println("Bookings generated with " + attempts + " attempts");
    }
}
