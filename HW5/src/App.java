import java.time.LocalDate;
import java.time.LocalDateTime;

import models.Airline;
import models.Booking;
import models.Person;
import models.flight.RegularFlight;
import models.flight.SpecificFlight;
import models.role.EmployeeRole;
import models.role.PassengerRole;
import services.FlightService;
import services.RoleService;

public class App {
    public static void main(String[] args) throws Exception {
        // Create 2 people with name "Alice" and "Bob".
        Person per1 = new Person("Alice");
        Person per2 = new Person("Bob");
        Person per3 = new Person("Charlie");

        // assign PasengerRole for p1 and p2.
        PassengerRole pas1 = RoleService.assignRole(per1, new PassengerRole());
        // STDOUT
        // ========== Before assignRole ==========
        // Person{id=1, name='Alice', roles=[]}
        // ========== After assignRole ==========
        // Person{id=1, name='Alice', roles=[PassengerRole{name=Alice, bookings=[]}]}
        // PassengerRole{name=Alice, bookings=[]}
        // ========== End assignRole ==========

        PassengerRole pas2 = RoleService.assignRole(per2, new PassengerRole());
        // STDOUT
        // ========== Before assignRole ==========
        // Person{id=2, name='Bob', roles=[]}
        // ========== After assignRole ==========
        // Person{id=2, name='Bob', roles=[PassengerRole{name=Bob, bookings=[]}]}
        // PassengerRole{name=Bob, bookings=[]}
        // ========== End assignRole =========

        // assign EployeeRole for p1 and p3.
        EmployeeRole emp1 = RoleService.assignRole(per1, new EmployeeRole());
        // STDOUT
        // ========== Before assignRole ==========
        // Person{id=1, name='Alice', roles=[PassengerRole{name=Alice, bookings=[]}]}
        // ========== After assignRole ==========
        // Person{id=1, name='Alice', roles=[PassengerRole{name=Alice, bookings=[]},
        // EmployeeRole{person name=Alice, supervisor=null, subordinates=[], date of
        // flights=[]}]}
        // EmployeeRole{person name=Alice, supervisor=null, subordinates=[], date of
        // flights=[]}
        // ========== End assignRole ==========

        EmployeeRole emp3 = RoleService.assignRole(per3, new EmployeeRole());
        // STDOUT
        // ========== Before assignRole ==========
        // Person{id=3, name='Charlie', roles=[]}
        // ========== After assignRole ==========
        // Person{id=3, name='Charlie', roles=[EmployeeRole{person name=Charlie,
        // supervisor=null, subordinates=[], date of flights=[]}]}
        // EmployeeRole{person name=Charlie, supervisor=null, subordinates=[], date of
        // flights=[]}
        // ========== End assignRole ==========

        // Print out the Person of p1 and p2.
        System.out.println(per1 + "\n" + per2 + "\n" + per3);
        // STDOUT
        // Person{id=1, name='Alice', roles=[PassengerRole{name=Alice, bookings=[]},
        // EmployeeRole{person name=Alice, supervisor=null, subordinates=[], date of
        // flights=[]}]}
        // Person{id=2, name='Bob', roles=[PassengerRole{name=Bob, bookings=[]}]}
        // Person{id=3, name='Charlie', roles=[EmployeeRole{person name=Charlie,
        // supervisor=null, subordinates=[], date of flights=[]}]}

        // Asiagn emp1 as a superviser to emp3.
        RoleService.assignSupervisor(emp3, emp1);
        // STDOUT
        // ========== Before assignSupervisor ==========
        // EmployeeRole{person name=Charlie, supervisor=null, subordinates=[], date of
        // flights=[]}
        // EmployeeRole{person name=Alice, supervisor=null, subordinates=[], date of
        // flights=[]}
        // ========== After assignSupervisor ==========
        // EmployeeRole{person name=Charlie, supervisor=Alice, subordinates=[], date of
        // flights=[]}
        // EmployeeRole{person name=Alice, supervisor=null, subordinates=[Charlie], date
        // of flights=[]}
        // ========== End assignSupervisor ==========

        // Remove emp1 as a superviser from emp3.
        RoleService.removeSubordinate(emp1, emp3);
        // STDOUT
        // ========== Before removeSubordinate ==========
        // EmployeeRole{person name=Alice, supervisor=null, subordinates=[Charlie], date
        // of flights=[]}
        // EmployeeRole{person name=Charlie, supervisor=Alice, subordinates=[], date of
        // flights=[]}
        // ========== After removeSubordinate ==========
        // EmployeeRole{person name=Alice, supervisor=null, subordinates=[], date of
        // flights=[]}
        // EmployeeRole{person name=Charlie, supervisor=null, subordinates=[], date of
        // flights=[]}
        // ========== End removeSubordinate ==========
        // Create an airline with id 1.

        Airline a1 = new Airline(1);

        // Add the people to the airline.
        FlightService.addPersonToAirline(a1, per1);
        // STDOUT
        // ========== Before addPersonToAirline ==========
        // Airline{id=1, people=[], regularFlights=[]}
        // Person{id=1, name='Alice', roles=[PassengerRole{name=Alice, bookings=[]},
        // EmployeeRole{person name=Alice, supervisor=null, subordinates=[], date of
        // flights=[]}]}
        // ========== After addPersonToAirline ==========
        // Airline{id=1, people=[Alice], regularFlights=[]}
        // Person{id=1, name='Alice', roles=[PassengerRole{name=Alice, bookings=[]},
        // EmployeeRole{person name=Alice, supervisor=null, subordinates=[], date of
        // flights=[]}], airline id=1}
        // ========== End addPersonToAirline ==========

        FlightService.addPersonToAirline(a1, per2);
        // STDOUT
        // ========== Before addPersonToAirline ==========
        // Airline{id=1, people=[Alice], regularFlights=[]}
        // Person{id=2, name='Bob', roles=[PassengerRole{name=Bob, bookings=[]}]}
        // ========== After addPersonToAirline ==========
        // Airline{id=1, people=[Alice, Bob], regularFlights=[]}
        // Person{id=2, name='Bob', roles=[PassengerRole{name=Bob, bookings=[]}],
        // airline id=1}
        // ========== End addPersonToAirline ==========

        // Create 1 regular flights with flight number 1.
        RegularFlight rf1 = FlightService.createRegularFlightForAirline(LocalDateTime.now(), 1, a1);
        // STDOUT
        // ========== Before createRegularFlightForAirline ==========
        // Airline{id=1, people=[Alice, Bob], regularFlights=[]}
        // ========== After createRegularFlightForAirline ==========
        // Airline{id=1, people=[Alice, Bob], regularFlights=[1]}
        // ========== End createRegularFlightForAirline ==========
        // Create 1 special flight for regular flight.

        SpecificFlight sf1 = FlightService.createSpecificFlightForRegularFlight(LocalDate.now(), rf1);
        // STDOUT
        // ========== Before createSpecificFlightForRegularFlight ==========
        // RegularFlight{time=2022-06-01T10:42:57.053820870, flightNumber=1,
        // airline=Airline{id=1, people=[Alice, Bob], regularFlights=[1]},
        // specificFlights number=0}
        // ========== After createSpecificFlightForRegularFlight ==========
        // RegularFlight{time=2022-06-01T10:42:57.053820870, flightNumber=1,
        // airline=Airline{id=1, people=[Alice, Bob], regularFlights=[1]},
        // specificFlights number=1}
        // ========== End createSpecificFlightForRegularFlight ==========

        // Add the EmployeeRole `emp1` to the specifc flight `sf1`.
        RoleService.assignEmployeeToSpecifcFlight(emp1, sf1);
        // STDOUT
        // ========== Before assignEmployeeToSpecifcFlight ==========
        // EmployeeRole{person name=Alice, supervisor=null, subordinates=[], date of
        // flights=[]}
        // SpecificFlight{date=2022-06-01, regularFlight=1, bookings=[], employees=[]}
        // ========== After assignEmployeeToSpecifcFlight ==========
        // EmployeeRole{person name=Alice, supervisor=null, subordinates=[], date of
        // flights=[2022-06-01]}
        // SpecificFlight{date=2022-06-01, regularFlight=1, bookings=[],
        // employees=[Alice]}
        // ========== End assignEmployeeToSpecifcFlight ==========

        // PassengerRole `pas1` book a seat with number 1 of specifc flight `sf1`.
        Booking b1 = RoleService.bookASpecificFlight(pas1, 1, sf1);
        // STDOUT
        // ========== Before bookASpecificFlight ==========
        // PassengerRole{name=Alice, bookings=[]}
        // SpecificFlight{date=2022-06-01, regularFlight=1, bookings=[],
        // employees=[Alice]}
        // ========== After bookASpecificFlight ==========
        // PassengerRole{name=Alice, bookings=[1]}
        // SpecificFlight{date=2022-06-01, regularFlight=1, bookings=[1],
        // employees=[Alice]}
        // ========== End bookASpecificFlight ==========
    }
}
