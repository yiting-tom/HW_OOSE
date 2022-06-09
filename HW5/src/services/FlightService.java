package services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import models.Airline;
import models.Person;
import models.flight.RegularFlight;
import models.flight.SpecificFlight;

public class FlightService {
    static boolean stdout = true;    

    /**
     * assign a person to an airline.
     * @param airline
     * @param person
     * @throws RuntimeException if the airline already has the person
     * @throws RuntimeException if the person is null
     * @throws RuntimeException if the role is null
     * @throws RuntimeException if the person is already assigned to an airline
     */
    public static void addPersonToAirline(Airline airline, Person person) {
        if (stdout) {
            System.out.println("\n========== Before addPersonToAirline ==========");
            System.out.println(airline);
            System.out.println(person);
        }

        if (airline == null) {
            throw new RuntimeException("airline is null");
        }

        if (person == null) {
            throw new RuntimeException("person is null");
        }

        // Get the list of persons from the airline.
        ArrayList<Person> people = airline.getPeople();

        // Check if the person is already assigned to the airline.
        if (people.contains(person)) {
            throw new RuntimeException("The person is already assigned to the airline.");
        }

        // Check if the airline is already assigned to the person.
        if (person.getAirline() != null) {
            throw new RuntimeException("The airline is already assigned to another person.");
        }

        // Add the new person to the list.
        people.add(person);

        person.setAirline(airline);

        if (stdout) {
            System.out.println("========== After addPersonToAirline ==========");
            System.out.println(airline);
            System.out.println(person);
            System.out.println("========== End addPersonToAirline ==========\n");
        }
    }

    /**
     * remove a role from a person.
     * @param airline
     * @param person
     * @throws RuntimeException if the person not in the airline
     * @throws RuntimeException if the person is null
     * @throws RuntimeException if the airline is null
     */
    public static void removePersonFromAirline(Airline airline, Person person) {
        if (stdout) {
            System.out.println("\n========== Before removePersonFromAirline ==========");
            System.out.println(airline);
        }

        if (airline == null) {
            throw new RuntimeException("airline is null");
        }

        if (person == null) {
            throw new RuntimeException("person is null");
        }

        // Get the list of persons from the airline.
        ArrayList<Person> people = airline.getPeople();

        // Check if the person is already assigned to the airline.
        if (!people.contains(person)) {
            throw new RuntimeException("The person is not assigned to the airline.");
        }

        // Remove the person from the list.
        people.remove(person);

        if (stdout) {
            System.out.println("========== After removePersonFromAirline ==========");
            System.out.println(airline);
            System.out.println("========== End removePersonFromAirline ==========\n");
        }
    }

    /**
     * remove all roles from a person.
     * @param airline
     * @throws RuntimeException if the airline is null
     * @throws RuntimeException if the airline doesn't have any person
     */
    public static void removeAllPeopleFromAirline(Airline airline) {
        if (stdout) {
            System.out.println("\n========== Before removeAllPeopleFromAirline ==========");
            System.out.println(airline);
        }

        if (airline == null) {
            throw new RuntimeException("airline is null");
        }

        // Get the list of persons from the airline.
        ArrayList<Person> people = airline.getPeople();

        // Remove all persons from the list.
        people.clear();

        if (stdout) {
            System.out.println("========== After removeAllPeopleFromAirline ==========");
            System.out.println(airline);
            System.out.println("========== End removeAllPeopleFromAirline ==========\n");
        }
    }

    /**
     * register a regular flight for the airline.
     * @param airline
     * @param regularFlight
     * @throws RuntimeException if the airline is null
     * @throws RuntimeException if the regularFlight is null
     * @throws RuntimeException if the regularFlight is already registered for the airline
     */
    public static void registerRegularFlightToAirline(Airline airline, RegularFlight regularFlight) {
        if (stdout) {
            System.out.println("\n========== Before registerRegularFlightToAirline ==========");
            System.out.println(airline);
        }

        if (airline == null) {
            throw new RuntimeException("airline is null");
        }

        if (regularFlight == null) {
            throw new RuntimeException("regularFlight is null");
        }

        // Get the list of regular flights from the airline.
        ArrayList<RegularFlight> regularFlights = airline.getRegularFlights();

        // Check if the regular flight is already assigned to the airline.
        if (regularFlights.contains(regularFlight)) {
            throw new RuntimeException("The regular flight is already assigned to the airline.");
        }

        // Add the new regular flight to the list.
        regularFlights.add(regularFlight);

        if (stdout) {
            System.out.println("========== After registerRegularFlightToAirline ==========");
            System.out.println(airline);
            System.out.println("========== End registerRegularFlightToAirline ==========\n");
        }
    }

    /**
     * create a regular flight for an airline.
     * @param time
     * @param airline
     * @throws RuntimeException if the airline is null
     * @throws RuntimeException if the time is null
     * @throws RuntimeException if the flight number is already assigned to the airline.
     */
    public static RegularFlight createRegularFlightForAirline(LocalDateTime time, int flightNumber, Airline airline) {
        if (stdout) {
            System.out.println("\n========== Before createRegularFlightForAirline ==========");
            System.out.println(airline);
        }

        if (airline == null) {
            throw new RuntimeException("airline is null");
        }

        if (time == null) {
            throw new RuntimeException("time is null");
        }

        // Get the list of regular flights from the airline.
        ArrayList<RegularFlight> regularFlights = airline.getRegularFlights();

        // Check if the flight number is already assigned to the airline.
        for (RegularFlight regularFlight : regularFlights) {
            if (regularFlight.getFlightNumber() == flightNumber) {
                throw new RuntimeException("The flight number is already assigned to the airline.");
            }
        }

        // Create the regular flight.
        RegularFlight regularFlight = new RegularFlight(time, flightNumber, airline);

        // Add the regular flight to the list.
        regularFlights.add(regularFlight);

        if (stdout) {
            System.out.println("========== After createRegularFlightForAirline ==========");
            System.out.println(airline);
            System.out.println("========== End createRegularFlightForAirline ==========\n");
        }

        return regularFlight;
    }

    /**
     * create a specifc flight for a regualr flight.
     * @param date 
     * @param regularFlight
     * @throws RuntimeException if the regular flight is null
     */
    public static SpecificFlight createSpecificFlightForRegularFlight(LocalDate date, RegularFlight regularFlight) {
        if (stdout) {
            System.out.println("\n========== Before createSpecificFlightForRegularFlight ==========");
            System.out.println(regularFlight);
        }

        if (regularFlight == null) {
            throw new RuntimeException("regularFlight is null");
        }

        // Get the list of specific flights from the regular flight.
        ArrayList<SpecificFlight> specificFlights = regularFlight.getSpecificFlights();

        // Create the specific flight.
        SpecificFlight specificFlight = new SpecificFlight(date, regularFlight);

        // Add the specific flight to the list.
        specificFlights.add(specificFlight);

        if (stdout) {
            System.out.println("========== After createSpecificFlightForRegularFlight ==========");
            System.out.println(regularFlight);
            System.out.println("========== End createSpecificFlightForRegularFlight ==========\n");
        }

        return specificFlight;
    }
}
