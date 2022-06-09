package models.role;

import models.Person;

public abstract class PersonRole {
    private int id;
    private Person person;

    private static int idCounter = 0;

    private static int nextId() {
        return ++idCounter;
    }

    public PersonRole() {
        this.setId(nextId());
    }

    public PersonRole(Person person) {
        this.setId(nextId());
        this.setPerson(person);
    }

    public PersonRole(int id, Person person) {
        this.setId(id);
        this.setPerson(person);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}