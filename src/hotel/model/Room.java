package hotel.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Room implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int roomNumber;
    private RoomType type;
    private boolean available;

    public Room(int roomNumber, RoomType type, boolean available) {
        this.roomNumber = roomNumber;
        this.type = Objects.requireNonNull(type, "Room type cannot be null");
        this.available = available;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = Objects.requireNonNull(type, "Room type cannot be null");
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", type=" + type +
                ", available=" + available +
                '}';
    }
}
