package services;

import java.util.ArrayList;

import models.Booking;
import models.Person;
import models.flight.SpecificFlight;
import models.role.EmployeeRole;
import models.role.PassengerRole;
import models.role.PersonRole;

public class RoleService {
    static boolean stdout = true;

    /**
     * assign a role to a person.
     * @param <T>
     * @param person
     * @param role
     * @return new role of the person
     * @throws RuntimeException if the person already has the role
     * @throws RuntimeException if the person is null
     * @throws RuntimeException if the role is null
     * @throws RuntimeException if the list of roles contains more than 2 roles after the addition
     * @throws RuntimeException if the role has already been assigned to a person
     */
    public static <T extends PersonRole> T assignRole(Person person, T role) {
        if (stdout) {
            System.out.println("\n========== Before assignRole ==========");
            System.out.println(person);
        }

        if (person == null) {
            throw new RuntimeException("person is null");
        }

        // Get the list of roles from the person.
        ArrayList<PersonRole> roles = person.getRoles();

        // Check it the list of roles contains more than 2 roles after adding this role.
        if (roles.size() > 2) {
            throw new RuntimeException("The person can't have more than 2 roles.");
        }


        // Create the new role.
        PersonRole newRole;

        // Deterine the type of role.
        if (role instanceof EmployeeRole) {
            newRole = new EmployeeRole();
        } else if (role instanceof PassengerRole) {
            newRole = new PassengerRole();
        } else {
            throw new RuntimeException("The role is not valid.");
        }

        // Add the new role to the list.
        assert newRole instanceof PersonRole;
        roles.add(newRole);

        // Add the person to the role.
        newRole.setPerson(person);

        if (stdout) {
            System.out.println("========== After assignRole ==========");
            System.out.println(person);
            System.out.println(newRole);
            System.out.println("========== End assignRole ==========\n");
        }

        return (T) newRole;
    }

    /**
     * remove a role from a person.
     * @param person
     * @param role
     * @throws RuntimeException if the person doesn't have the role
     * @throws RuntimeException if the person is null
     * @throws RuntimeException if the role is null
     * @throws RUntimeException if the person is not assigned to the role
     */
    public static void removeRole(Person person, PersonRole role) {
        if (stdout) {
            System.out.println("\n========== Before removeRole ==========");
            System.out.println(person);
        }

        if (person == null) {
            throw new RuntimeException("person is null");
        }

        if (role == null) {
            throw new RuntimeException("role is null");
        }

        // Get the list of roles from the person.
        ArrayList<PersonRole> roles = person.getRoles();

        // Check if the role is assigned to the person.
        if (!roles.contains(role)) {
            throw new RuntimeException("The role is not assigned to the person.");
        }

        // Check if the person is assigned to the role.
        if (role.getPerson() != person) {
            throw new RuntimeException("The role is not assigned to the person.");
        }

        // Remove the role from the list.
        roles.remove(role);

        // Remove the person from the role.
        role.setPerson(null);

        if (stdout) {
            System.out.println("========== After removeRole ==========");
            System.out.println(person);
            System.out.println(role);
            System.out.println("========== End removeRole ==========\n");
        }
    }

    /**
     * remove all roles from a person.
     * @param person
     * @throws RuntimeException if the person is null
     * @throws RuntimeException if the list of roles contains nothings
     */
    public static void removeAllRoles(Person person) {
        if (stdout) {
            System.out.println("\n========== Before removeAllRoles ==========");
            System.out.println(person);
        }

        if (person == null) {
            throw new RuntimeException("person is null");
        }

        // Get the list of roles from the person.
        ArrayList<PersonRole> roles = person.getRoles();

        if (roles.size() == 0) {
            throw new RuntimeException("The person doesn't have any roles.");
        }

        // Remove all roles from the list.
        roles.clear();

        // Remove the person from all roles.
        for (PersonRole role : roles) {
            role.setPerson(null);
        }

        if (stdout) {
            System.out.println("========== After removeAllRoles ==========");
            System.out.println(person);
            System.out.println(roles);
            System.out.println("========== End removeAllRoles ==========\n");
        }
    }

    /**
     * assign an employee to another employee as a supervisor.
     * @param subordinate
     * @param supervisor
     */
    public static void assignSupervisor(EmployeeRole subordinate, EmployeeRole supervisor) {
        if (stdout) {
            System.out.println("\n========== Before assignSupervisor ==========");
            System.out.println(subordinate);
            System.out.println(supervisor);
        }

        if (subordinate == null) {
            throw new RuntimeException("subordinate is null");
        }

        if (supervisor == null) {
            throw new RuntimeException("supervisor is null");
        }

        // Get the list of subordinates from the supervisor.
        ArrayList<EmployeeRole> subordinates = supervisor.getSubordinates();

        // Check if the subordinate is already assigned to the supervisor.
        if (subordinates.contains(subordinate)) {
            throw new RuntimeException("The subordinate is already assigned to the supervisor.");
        }

        // Check if the subordinate is already assigned to another supervisor.
        if (subordinate.getSupervisor() != null) {
            throw new RuntimeException("The subordinate is already assigned to another supervisor.");
        }

        // Add the subordinate to the list of subordinates of the supervisor.
        subordinates.add(subordinate);

        // Set the supervisor of the subordinate.
        subordinate.setSupervisor(supervisor);

        if (stdout) {
            System.out.println("========== After assignSupervisor ==========");
            System.out.println(subordinate);
            System.out.println(supervisor);
            System.out.println("========== End assignSupervisor ==========\n");
        }
    }

    /**
     * remove an subordinate from a supervisor.
     * @param supervisor
     * @param subordinate
     * @throws RuntimeException if the subordinate is null
     * @throws RuntimeException if the supervisor is null
     */
    public static void removeSubordinate(EmployeeRole supervisor, EmployeeRole subordinate) {
        if (stdout) {
            System.out.println("\n========== Before removeSubordinate ==========");
            System.out.println(supervisor);
            System.out.println(subordinate);
        }

        if (supervisor == null) {
            throw new RuntimeException("supervisor is null");
        }

        if (subordinate == null) {
            throw new RuntimeException("subordinate is null");
        }

        // Get the list of subordinates from the supervisor.
        ArrayList<EmployeeRole> subordinates = supervisor.getSubordinates();

        // Check if the subordinate is assigned to the supervisor.
        if (!subordinates.contains(subordinate)) {
            throw new RuntimeException("The subordinate is not assigned to the supervisor.");
        }

        // Remove the subordinate from the list of subordinates of the supervisor.
        subordinates.remove(subordinate);

        // Set the supervisor of the subordinate to null.
        subordinate.setSupervisor(null);

        if (stdout) {
            System.out.println("========== After removeSubordinate ==========");
            System.out.println(supervisor);
            System.out.println(subordinate);
            System.out.println("========== End removeSubordinate ==========\n");
        }
    }

    /**
     * book a specific flight for a passenger.
     * @param role
     * @param seatNumber
     * @param specificFlight
     * @throws RuntimeException if the role is null
     * @throws RuntimeException if the specificFlight is null
     * @throws RuntimeException if the seatNumber already has a booking
     * @throws RuntimeException if the role already booked this specificFlight
     */
    public static Booking bookASpecificFlight(PassengerRole role, int seatNumber, SpecificFlight specificFlight) {
        if (stdout) {
            System.out.println("\n========== Before bookASpecificFlight ==========");
            System.out.println(role);
            System.out.println(specificFlight);
        }

        if (role == null) {
            throw new RuntimeException("role is null");
        }

        if (specificFlight == null) {
            throw new RuntimeException("specificFlight is null");
        }

        // Get the list of bookings from the role.
        ArrayList<Booking> roleBookines = role.getBookings();

        // Check if the role has booked this specificFlight already.
        for (Booking booking : roleBookines) {
            if (booking.getSpecificFlight() == specificFlight) {
                throw new RuntimeException("The role has already booked this specificFlight.");
            }
        }

        // Get the list of bookings from the specificFlight.
        ArrayList<Booking> specificFlightBookings = specificFlight.getBookings();

        // Check if the seat of specificFlight is already booked.
        for (Booking booking : specificFlightBookings) {
            if (booking.getSeatNumber() == seatNumber) {
                throw new RuntimeException("The seat is already booked.");
            }
        }

        // Create a new booking.
        Booking booking = new Booking(seatNumber, specificFlight, role);

        // Add the booking to the list of bookings of the role.
        roleBookines.add(booking);

        // Add the booking to the list of bookings of the specificFlight.
        specificFlightBookings.add(booking);

        if (stdout) {
            System.out.println("========== After bookASpecificFlight ==========");
            System.out.println(role);
            System.out.println(specificFlight);
            System.out.println("========== End bookASpecificFlight ==========\n");
        }

        return booking;
    }


    /**
     * assign a employee to a specific flight
     * @param employee
     * @param specificFlight
     */
    public static void assignEmployeeToSpecifcFlight(EmployeeRole employee, SpecificFlight specificFlight) {
        if (stdout) {
            System.out.println("\n========== Before assignEmployeeToSpecifcFlight ==========");
            System.out.println(employee);
            System.out.println(specificFlight);
        }

        if (employee == null) {
            throw new RuntimeException("employee is null");
        }

        if (specificFlight == null) {
            throw new RuntimeException("specificFlight is null");
        }

        // Get the list of employees from the specificFlight.
        ArrayList<EmployeeRole> employees = specificFlight.getEmployees();

        // Check if the employee is already assigned to the specificFlight.
        if (employees.contains(employee)) {
            throw new RuntimeException("The employee is already assigned to the specificFlight.");
        }

        // Get the list of specificFlights from the employee.
        ArrayList<SpecificFlight> flights = employee.getFlights();

        // Check if the specificFlight is already assigned to the employee.
        if (flights.contains(specificFlight)) {
            throw new RuntimeException("The specificFlight is already assigned to the employee.");
        }

        // Add the employee to the list of employees of the specificFlight.
        employees.add(employee);

        // Add the specificFlight to the list of specificFlights of the employee.
        flights.add(specificFlight);

        if (stdout) {
            System.out.println("========== After assignEmployeeToSpecifcFlight ==========");
            System.out.println(employee);
            System.out.println(specificFlight);
            System.out.println("========== End assignEmployeeToSpecifcFlight ==========\n");
        }
    }
}
