package models;
import models.Employee;
import models.Person;

public class BoardMember extends Person {
    private BoardOfDirectors boardOfDirectors;

    public BoardMember(Person person, BoardOfDirectors boardOfDirectors) {
        super(person.getFirstName(), person.getLastName(), person.getSex(), person.getAge());
        this.setBoardOfDirectors(boardOfDirectors);
    }
    public BoardMember(Employee employee, BoardOfDirectors boardOfDirectors) {
        super(employee.getFirstName(), employee.getLastName(), employee.getSex(), employee.getAge());
        this.setBoardOfDirectors(boardOfDirectors);
    }
    public BoardMember(String firstName, String lastName, String sex, int age, BoardOfDirectors boardOfDirectors) {
        super(firstName, lastName, sex, age);
        this.setBoardOfDirectors(boardOfDirectors);
    }

    public void setBoardOfDirectors(BoardOfDirectors boardOfDirectors) {
        this.boardOfDirectors = boardOfDirectors;
    }
    public BoardOfDirectors getBoardOfDirectors() {
        return this.boardOfDirectors;
    }
}