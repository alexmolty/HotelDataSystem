package hotel.service;

import hotel.model.Booking;
import hotel.model.RoomType;
import hotel.model.RoomTypeName;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileService {
    public void saveRoomTypes(List<RoomType> typesRooms, String filename) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(filename))) {
            // размер списка
            out.writeInt(typesRooms.size());

            for (RoomType tr : typesRooms) {
                out.writeUTF(tr.getRoomTypeName().toString());
                out.writeDouble(tr.getPricePerNight());
                out.writeInt(tr.getCapacity());
            }
            System.out.println("Successfully wrote " + typesRooms.size() + " room types in folder: " + filename);
        } catch (IOException e) {
            System.err.println("Error writing: " + e.getMessage());
        }
    }
    public List<RoomType> readRoomTypes(String filename) {
        String name;
        double pricePerNight;
        int capacity;
        List<RoomType> roomTypes = new LinkedList<>();
        try (DataInputStream in = new DataInputStream(new FileInputStream(filename))) {
            int size = in.readInt();

            for (int i = 0; i < size; i++) {
                name = in.readUTF();
                pricePerNight = in.readDouble();
                capacity = in.readInt();
                RoomTypeName roomTypeName = RoomTypeName.valueOf(name);
                RoomType r = new RoomType(roomTypeName, pricePerNight, capacity);
                roomTypes.add(r);
            }
            System.out.println("Restored successfully " + roomTypes.size() + " room types");
        } catch (IOException e) {
            System.err.println("Error reading: " + e.getMessage());
        }
        return roomTypes;
    }

    public void saveBookings(List<Booking> bookings, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(bookings);
            System.out.println("Bookings data written successfully in file: " + filename);
        } catch (IOException e) {
            System.err.println("Error writing file " + filename + ": " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    public List<Booking> readBookings(String filename) {
        List<Booking> bookings = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            bookings = (List<Booking>) in.readObject();
            System.out.println("Restored successfully " + bookings.size() + " bookings");
        } catch (IOException e) {
            System.err.println("Error reading " + filename + ": " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + filename);
        }
        return bookings;
    }
}
