package models.role;

import java.util.ArrayList;

import models.Booking;

public class PassengerRole extends PersonRole {
    private ArrayList<Booking> bookings = new ArrayList<>();

    public PassengerRole() {
        super();
    }

    public PassengerRole(ArrayList<Booking> bookings) {
        super();
        this.setBookings(bookings);
    }

    @Override
    public String toString() {
        return "PassengerRole{" +
                "name=" + this.getPerson().getName() +
                ", bookings=" + this.bookingsString() +
                '}';
    }

    public String bookingsString() {
        ArrayList<String> names = new ArrayList<>();
        for (Booking b : this.getBookings()) {
            names.add(String.valueOf(b.getSeatNumber()));
        }
        return "[" + String.join(", ", names) + "]";
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }
}