package hotel.model;

import java.io.Serial;
import java.io.Serializable;

public class RoomType implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private RoomTypeName name;
    private double pricePerNight;
    private int capacity;

    public RoomType(RoomTypeName name, double pricePerNight, int capacity) {
        this.name = name;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
    }

    public RoomTypeName getRoomTypeName() {
        return name;
    }

    public void setRoomTypeName(RoomTypeName name) {
        this.name = name;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "name='" + name + '\'' +
                ", pricePerNight=" + pricePerNight +
                ", capacity=" + capacity +
                '}';
    }
}
