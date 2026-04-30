package test;

import hotel.model.*;
import hotel.service.HotelManagingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class HotelManagingServiceTest {

    private Hotel hotel;
    private HotelManagingService manager;

    @BeforeEach
    void setUp() {
        hotel = new Hotel("Test Hotel");
        manager = new HotelManagingService(hotel);
    }

    @Test
    void testRegisterRoomType_Success() {
        RoomType type = new RoomType("Standard", 100.0, 2);
        assertTrue(manager.registerRoomType(type));

        RoomType duplicateType = new RoomType("STANDARD", 150.0, 3);
        assertThrows(IllegalStateException.class, () -> manager.registerRoomType(duplicateType));
    }

    @Test
    void testRemoveRoomType_CaseInsensitive() {
        RoomType type = new RoomType("Deluxe", 200.0, 2);
        manager.registerRoomType(type);

        assertTrue(manager.removeRoomType("DELUXE"));
        assertNull(hotel.getRoomTypes().get("deluxe"));
    }

    @Test
    void testRemoveRoomType_UsedByRoom_ThrowsException() {
        RoomType type = new RoomType("Royal", 500.0, 4);
        manager.registerRoomType(type);

        Room room = new Room(101, type);
        manager.registerRoom(room);

        assertThrows(IllegalStateException.class, () -> manager.removeRoomType("Royal"));
    }

    @Test
    void testCreateBooking_Success() {
        Guest guest = new Guest(1, "Alex", "alex@test.com", "pass", LocalDate.of(1999, 9, 9));
        manager.registerGuest(guest);

        RoomType type = new RoomType("Standard", 100.0, 2);
        manager.registerRoomType(type);
        Room room = new Room(101, type);
        manager.registerRoom(room);

        LocalDate checkIn = LocalDate.now().plusDays(1);
        LocalDate checkOut = LocalDate.now().plusDays(5);

        Booking booking = manager.createBooking(guest, room, checkIn, checkOut);

        assertNotNull(booking);
        assertEquals(1, hotel.getBookings().size());
        assertEquals(400.0, booking.getTotalPrice()); // 4 ночи по 100$
    }
}