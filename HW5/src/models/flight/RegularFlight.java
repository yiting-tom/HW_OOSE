package models.flight;

import java.time.LocalDateTime;
import java.util.ArrayList;

import models.Airline;

public class RegularFlight {
    private LocalDateTime time;
    private int flightNumber;
    private Airline airline;
    private ArrayList<SpecificFlight> specificFlights = new ArrayList<>();

    private static int flightNumberCounter;
    private static int nextFlightNumber() {
        return flightNumberCounter++;
    }

    public RegularFlight(LocalDateTime time, Airline airline) {
        this.setTime(time);
        this.setFlightNumber(nextFlightNumber());
        this.setAirline(airline);
    }

    public RegularFlight(LocalDateTime time, int flightNumber, Airline airline) {
        this.setTime(time);
        this.setFlightNumber(flightNumber);
        this.setAirline(airline);
    }

    public RegularFlight(LocalDateTime time, int flightNumber, Airline airline, ArrayList<SpecificFlight> specificFlights) {
        this(time, flightNumber, airline);
        this.setSpecificFlights(specificFlights);
    }

    @Override
    public String toString() {
        return "RegularFlight{" +
                "time=" + time +
                ", flightNumber=" + flightNumber +
                ", airline=" + airline +
                ", specificFlights number=" + specificFlights.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegularFlight that = (RegularFlight) o;

        if (!time.equals(that.time)) return false;
        return flightNumber == that.flightNumber;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public ArrayList<SpecificFlight> getSpecificFlights() {
        return specificFlights;
    }

    public void setSpecificFlights(ArrayList<SpecificFlight> specificFlights) {
        this.specificFlights = specificFlights;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

}
