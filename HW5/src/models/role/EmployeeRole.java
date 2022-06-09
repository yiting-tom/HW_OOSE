package models.role;

import java.util.ArrayList;

import models.Person;
import models.flight.SpecificFlight;

public class EmployeeRole extends PersonRole {
    private EmployeeRole supervisor;
    private ArrayList<EmployeeRole> subordinates = new ArrayList<>();
    private ArrayList<SpecificFlight> flights = new ArrayList<>();

    public EmployeeRole() {
        super();
    }

    public EmployeeRole(Person person) {
        super(person);
    }

    public EmployeeRole(Person person, EmployeeRole supervisor) {
        super(person);
        this.setSupervisor(supervisor);
    }

    public EmployeeRole(Person person, EmployeeRole supervisor, ArrayList<EmployeeRole> subordinates) {
        super(person);
        this.setSupervisor(supervisor);
        this.setSubordinates(subordinates);
    }

    @Override
    public String toString() {
        return "EmployeeRole{" +
                "person name=" + this.getPerson().getName() +
                ", supervisor=" + this.supervisorString() +
                ", subordinates=" + this.subboardinatString() +
                ", date of flights=" + this.flightString() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EmployeeRole)) {
            return false;
        }
        EmployeeRole other = (EmployeeRole) obj;
        return this.getId() == other.getId() &&
                this.getPerson().equals(other.getPerson()) &&
                this.getSupervisor().equals(other.getSupervisor()) &&
                this.getSubordinates().equals(other.getSubordinates());
    }

    public String supervisorString() {
        if (this.getSupervisor() == null) {
            return "null";
        }
        return this.getSupervisor().getPerson().getName();
    }

    public String flightString() {
        ArrayList<String> names = new ArrayList<>();
        for (SpecificFlight f : this.getFlights()) {
            names.add(f.getDate().toString());
        }
        return "[" + String.join(", ", names) + "]";
    }

    public String subboardinatString() {
        if (this.getSubordinates().size() == 0) {
            return "[]";
        }
        ArrayList<String> names = new ArrayList<>();
        for (EmployeeRole p : this.getSubordinates()) {
            names.add(p.getPerson().getName());
        }
        return "[" + String.join(", ", names) + "]";
    }

    public void jobFunction() {
        System.out.println(this.toString() + " is doing something");
    }

    public EmployeeRole getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(EmployeeRole supervisor) {
        this.supervisor = supervisor;
    }

    public ArrayList<EmployeeRole> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(ArrayList<EmployeeRole> subordinates) {
        this.subordinates = subordinates;
    }

    public ArrayList<SpecificFlight> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<SpecificFlight> flights) {
        this.flights = flights;
    }
}