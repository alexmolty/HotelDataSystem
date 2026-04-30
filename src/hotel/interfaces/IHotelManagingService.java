package hotel.interfaces;

import hotel.model.Booking;
import hotel.model.Guest;
import hotel.model.Room;
import hotel.model.RoomType;

import java.time.LocalDate;

/**
 * Main service for managing hotel business processes.
 * Responsible for registering and removing entities, as well as managing bookings.
 */
public interface IHotelManagingService {

    /**
     * Registers a new room type in the hotel.
     *
     * @param roomType the room type object to register.
     * @return true if the room type is successfully registered.
     * @throws NullPointerException if the provided roomType is null.
     * @throws IllegalStateException if a room type with this name already exists.
     */
    boolean registerRoomType(RoomType roomType);

    /**
     * Registers a new physical room.
     *
     * @param room the room object to register.
     * @return true if the room is successfully added.
     * @throws NullPointerException if the provided room is null.
     * @throws IllegalStateException if the specified room type does not exist or the room number is already taken.
     */
    boolean registerRoom(Room room);

    /**
     * Registers a new guest in the hotel database.
     *
     * @param guest the guest object to register.
     * @throws NullPointerException if the provided guest object is null.
     * @throws IllegalStateException if a guest with this ID already exists.
     */
    void registerGuest(Guest guest);

    /**
     * Checks if a room is available for a specified period.
     *
     * @param room the room to check.
     * @param start the expected check-in date.
     * @param end the expected check-out date.
     * @return true if the room is available for the given dates, false otherwise.
     * @throws NullPointerException if any of the arguments are null.
     */
    boolean isRoomAvailableForDates(Room room, LocalDate start, LocalDate end);

    /**
     * Creates a new booking using individual parameters.
     *
     * @param guest the guest making the booking.
     * @param room the room to be booked.
     * @param checkIn the check-in date.
     * @param checkOut the check-out date.
     * @return the created booking object.
     * @throws IllegalArgumentException if the guest/room is not registered, or if the check-out date is before the check-in date.
     * @throws IllegalStateException if the room is occupied for the selected dates.
     */
    Booking createBooking(Guest guest, Room room, LocalDate checkIn, LocalDate checkOut);

    /**
     * Creates a booking based on a prepared Booking object.
     *
     * @param booking the prepared booking object.
     * @return the registered booking object.
     * @throws IllegalArgumentException if the data inside the object is invalid or not found in the database.
     * @throws IllegalStateException if the room is occupied for the selected dates.
     */
    Booking createBooking(Booking booking);

    /**
     * Removes a booking by its unique identifier.
     *
     * @param bookingId the ID of the booking to remove.
     * @return true if the booking is found and successfully removed.
     */
    boolean removeBooking(int bookingId);

    /**
     * Removes a room from the hotel fund.
     *
     * @param roomNumber the number of the room to remove.
     * @return true if the room is successfully removed.
     * @throws IllegalStateException if the room does not exist or has related bookings.
     */
    boolean removeRoom(int roomNumber);

    /**
     * Removes a room type from the system.
     *
     * @param roomTypeName the name of the room type to remove.
     * @return true if the room type is successfully removed.
     * @throws IllegalArgumentException if the provided name is null or empty.
     * @throws IllegalStateException if there are existing rooms tied to this room type.
     */
    boolean removeRoomType(String roomTypeName);

    /**
     * Removes a guest from the database.
     *
     * @param guestId the ID of the guest to remove.
     * @return true if the guest is successfully removed.
     * @throws IllegalStateException if the guest has related bookings.
     */
    boolean removeGuest(int guestId);
}