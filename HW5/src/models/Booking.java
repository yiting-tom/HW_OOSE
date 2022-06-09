package models;

import models.flight.SpecificFlight;
import models.role.PassengerRole;

public class Booking {
    private int seatNumber;
    private SpecificFlight specificFlight;
    private PassengerRole passenger;

    public Booking(int seatNumber, SpecificFlight specificFlight, PassengerRole passenger) {
        this.seatNumber = seatNumber;
        this.specificFlight = specificFlight;
        this.passenger = passenger;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "seatNumber=" + seatNumber +
                ", specificFlight=" + specificFlight +
                ", passenger=" + passenger +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking that = (Booking) o;

        if (seatNumber != that.seatNumber) return false;
        return specificFlight.equals(that.specificFlight);
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public SpecificFlight getSpecificFlight() {
        return specificFlight;
    }

    public void setSpecificFlight(SpecificFlight specificFlight) {
        this.specificFlight = specificFlight;
    }

    public PassengerRole getPassenger() {
        return passenger;
    }
    
    public void setPassenger(PassengerRole passenger) {
        this.passenger = passenger;
    }
}
