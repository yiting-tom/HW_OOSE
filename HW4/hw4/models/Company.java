package models;
import java.util.ArrayList;
import java.util.UUID;

public class Company {
    private String name;
    private UUID id;
    private ArrayList<Employee> employees = new ArrayList<Employee>();
    private ArrayList<Office> offices = new ArrayList<Office>();
    private BoardOfDirectors boardOfDirectors; 

    public Company(String name) {
        this.setName(name);
        this.setCompanyId(UUID.randomUUID());
    }
    public Company(String name, ArrayList<Employee> employees) {
        this(name);
        this.setEmployees(employees);
    }
    public Company(String name, ArrayList<Employee> employees, BoardOfDirectors boardOfDirectors) {
        this(name, employees);
        this.setBoardOfDirectors(boardOfDirectors);
    }

    @Override
    public String toString() {
        String appendix = this.getBoardOfDirectors() != null ? "and " + this.boardOfDirectors.size() + " board of directors" : "";
        appendix += this.getOffices().size() > 0 ? " and " + this.getOffices().size() + " offices" : "";
        return "<" + this.getClass().getName() + ": " + this.name + "> " + " has " + this.employees.size() + " employees " + appendix;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Company) {
            Company other = (Company) o;
            return this.getCompanyId().equals(other.getCompanyId());
        }
        return false;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }
    private void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public BoardOfDirectors getBoardOfDirectors() {
        return boardOfDirectors;
    }
    public void setBoardOfDirectors(BoardOfDirectors boardOfDirectors) {
        this.boardOfDirectors = boardOfDirectors;
    }

    public void setCompanyId(UUID id) {
        this.id = id;
    }
    public UUID getCompanyId() {
        return this.id;
    }

    public void addOffice(Office office) {
        this.offices.add(office);
    }
    public ArrayList<Office> getOffices() {
        return this.offices;
    }

}