package hotel.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Booking implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static int counter = 1;
    private int bookingId;
    private Guest guest;
    private Room room;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Booking(Guest guest, Room room, LocalDate checkIn, LocalDate checkOut) {
        this.guest = Objects.requireNonNull(guest, "Guest cannot be null");
        this.room = Objects.requireNonNull(room, "Room cannot be null");
        this.checkIn = Objects.requireNonNull(checkIn, "Check in cannot be null");
        this.checkOut = Objects.requireNonNull(checkOut, "Check out cannot be null");

        if (checkOut.isBefore(checkIn)) {
            throw new IllegalArgumentException("checkOut date cannot be before checkIn");
        }
        this.bookingId = counter++;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = Objects.requireNonNull(guest, "Guest cannot be null");
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = Objects.requireNonNull(room, "Room cannot be null");
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = Objects.requireNonNull(checkIn, "Check in cannot be null");
        if (this.checkOut.isBefore(checkIn)) {
            throw new IllegalArgumentException("checkIn date cannot be after checkOut");
        }
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = Objects.requireNonNull(checkOut, "Check out cannot be null");
        if (checkOut.isBefore(this.checkIn)) {
            throw new IllegalArgumentException("checkOut date cannot be before checkIn");
        }
    }

    public int getBookingId() {
        return bookingId;
    }

    public long getNights() {
        return ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    public double getTotalPrice() {
        return getNights() * room.getType().getPricePerNight();
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", guest=" + guest +
                ", room=" + room +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", nights=" + getNights() +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return bookingId == booking.bookingId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bookingId);
    }
}
