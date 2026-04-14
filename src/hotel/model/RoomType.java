package hotel.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

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

    public double getPricePerNight() {
        return pricePerNight;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "name='" + name + '\'' +
                ", pricePerNight=" + pricePerNight +
                ", capacity=" + capacity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RoomType roomType = (RoomType) o;
        return Double.compare(pricePerNight, roomType.pricePerNight) == 0 && capacity == roomType.capacity && name == roomType.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pricePerNight, capacity);
    }
}
