package models;

import java.util.ArrayList;
import models.role.PersonRole;

public class Person {
    private int id;
    private String name;
    private ArrayList<PersonRole> roles = new ArrayList<>();
    private Airline airline;

    private static int idCounter = 0;

    private static int nextId() {
        return ++idCounter;
    }

    public Person(String name) {
        this.setId(nextId());
        this.setName(name);
    }

    public Person(int id, String name) {
        this.setId(id);
        this.setName(name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roles=" + roles +
                (
                    this.getAirline() != null ? ", airline id=" + airline.getId() : ""
                ) +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        return name.equals(person.name);
    }


    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<PersonRole> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<PersonRole> roles) {
        if (roles.size() > 2) {
            throw new IllegalArgumentException("Person can't have more than 2 roles");
        }
        this.roles = roles;
    }
}
