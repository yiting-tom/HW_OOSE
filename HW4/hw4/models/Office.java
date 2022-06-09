package models;
import java.util.ArrayList;

public class Office {
    private String name;
    private Company company;
    private ArrayList<Employee> employees = new ArrayList<Employee>();

    public Office(Company company, String name) {
        this.setName(name);
        this.setCompany(company);
    }
    public Office(Company company, String name, ArrayList<Employee> employees) {
        this(company, name);
        this.setEmployees(employees);
    }

    @Override
    public String toString() {
        String appendix = (this.getEmployees() != null && this.getEmployees().size() > 0) ? " with " + this.getEmployees().size() + " employees" : "";
        return "<" + this.getClass().getName() + ": " + this.name + "> " + appendix;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Office) {
            return this.getName().equals(((Office) obj).getName());
        }
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    public Company getCompany() {
        return this.company;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
    public ArrayList<Employee> getEmployees() {
        return this.employees;
    }
}