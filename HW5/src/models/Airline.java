package models;

import java.util.ArrayList;

import models.flight.RegularFlight;

public class Airline {
    private int id;
    private ArrayList<Person> people = new ArrayList<>();
    private ArrayList<RegularFlight> regularFlights = new ArrayList<>();

    public Airline(int id) {
        this.id = id;
    }

    public Airline(int id, ArrayList<Person> people, ArrayList<RegularFlight> regularFlights) {
        this.id = id;
        this.people = people;
        this.regularFlights = regularFlights;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "id=" + id +
                ", people=" + this.peopleString() +
                ", regularFlights=" + this.regularFlightsString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airline airline = (Airline) o;

        if (id != airline.id) return false;
        return people.equals(airline.people);
    }

    public String peopleString() {
        ArrayList<String> names = new ArrayList<>();
        for (Person p : this.getPeople()) {
            names.add(p.getName());
        }
        return "[" + String.join(", ", names) + "]";
    }

    public String regularFlightsString() {
        ArrayList<String> names = new ArrayList<>();

        for (RegularFlight rf : this.getRegularFlights()) {
            names.add(String.valueOf(rf.getFlightNumber()));
        }
        return "[" + String.join(", ", names) + "]";
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }

    public ArrayList<RegularFlight> getRegularFlights() {
        return regularFlights;
    }

    public void setRegularFlights(ArrayList<RegularFlight> regularFlights) {
        this.regularFlights = regularFlights;
    }
}
