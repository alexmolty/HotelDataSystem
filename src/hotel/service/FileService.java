package hotel.service;

import hotel.interfaces.IFileService;
import hotel.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class FileService implements IFileService {
    private final Hotel hotel;

    public FileService(Hotel hotel) {
        this.hotel = Objects.requireNonNull(hotel);
    }

    // SAVING

    @Override
    public void saveGuests(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))) {
            var guests = hotel.getGuests();
            out.writeObject(guests);
            System.out.println("Successfuly wrote " + guests.size() + " guests.");
        } catch (IOException e) {
            System.err.println("Error saving guests:" + e.getMessage());
        }
    }

    @Override
    public void saveRoomTypes(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))) {
            var roomTypes = hotel.getRoomTypes();
            out.writeObject(roomTypes);
            System.out.println("Successfuly wrote " + roomTypes.size() + " room types.");
        } catch (IOException e) {
            System.err.println("Error saving room types: " + e.getMessage());
        }
    }

    @Override
    public void saveBookings(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))) {
            var bookings = hotel.getBookings();
            out.writeObject(bookings);
            System.out.println(bookings.size() + " bookings written successfully in file: " + filename);
        } catch (IOException e) {
            System.err.println("Error saving bookings " + filename + ": " + e.getMessage());
        }
    }

    @Override
    public void saveRooms(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))) {
            var rooms = hotel.getRooms();
            out.writeObject(rooms);
            System.out.println(rooms.size() + " rooms written successfully in file: " + filename);
        } catch (IOException e) {
            System.err.println("Error writing file " + filename + ": " + e.getMessage());
        }
    }

    // READING

    @SuppressWarnings("unchecked")
    @Override
    public Map<Integer, Guest> readGuests(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
            return (Map<Integer, Guest>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading guests: " + e.getMessage());
            return new HashMap<>();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, RoomType> readRoomTypes(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
            return (Map<String, RoomType>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading room types: " + e.getMessage());
            return new HashMap<>();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<Integer, Booking> readBookings(String filename) {
        Map<Integer, Booking> bookings = new HashMap<>();
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
            bookings = (Map<Integer, Booking>) in.readObject();
            System.out.println("Restored successfully " + bookings.size() + " bookings");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading bookings " + filename + ": " + e.getMessage());
        }
        return bookings;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<Integer, Room> readRooms(String filename) {
        Map<Integer, Room> rooms = new HashMap<>();
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
            rooms = (Map<Integer, Room>) in.readObject();
            System.out.println("Restored successfully " + rooms.size() + " rooms");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading " + filename + ": " + e.getMessage());
        }
        return rooms;
    }
}
