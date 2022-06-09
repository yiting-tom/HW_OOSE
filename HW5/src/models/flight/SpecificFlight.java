package models.flight;

import java.time.LocalDate;
import java.util.ArrayList;

import models.Booking;
import models.role.EmployeeRole;

public class SpecificFlight {
    private LocalDate date;
    private RegularFlight regularFlight;
    private ArrayList<Booking> bookings = new ArrayList<>();
    private ArrayList<EmployeeRole> employees = new ArrayList<>();

    public SpecificFlight(LocalDate date, RegularFlight regularFlight) {
        this.setDate(date);
        this.setRegularFlight(regularFlight);
    }

    public SpecificFlight(LocalDate date, RegularFlight regularFlight, ArrayList<Booking> bookings,
            ArrayList<EmployeeRole> employees) {
        this(date, regularFlight);
        this.setBookings(bookings);
        this.setEmployees(employees);
    }

    @Override
    public String toString() {
        return "SpecificFlight{" +
                "date=" + this.getDate() +
                ", regularFlight=" + this.getRegularFlight().getFlightNumber() +
                ", bookings=" + this.bookingsString() +
                ", employees=" + this.employeesString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        SpecificFlight that = (SpecificFlight) o;

        if (!date.equals(that.date))
            return false;
        return regularFlight.equals(that.regularFlight);
    }

    public String bookingsString() {
        ArrayList<String> names = new ArrayList<>();
        for (Booking b : this.getBookings()) {
            names.add(String.valueOf(b.getSeatNumber()));
        }
        return "[" + String.join(", ", names) + "]";
    }

    public String employeesString() {
        ArrayList<String> names = new ArrayList<>();
        for (EmployeeRole e : this.getEmployees()) {
            names.add(e.getPerson().getName());
        }
        return "[" + String.join(", ", names) + "]";
    }

    public RegularFlight getRegularFlight() {
        return regularFlight;
    }

    public void setRegularFlight(RegularFlight regularFlight) {
        this.regularFlight = regularFlight;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    public ArrayList<EmployeeRole> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<EmployeeRole> employees) {
        this.employees = employees;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
