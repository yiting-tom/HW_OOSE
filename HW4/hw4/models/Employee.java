package models;
import java.util.UUID;

/**
 * The Employee object.
 * 
 * Could be instantiated by the {@link hw4.services.CompanyService#admission()} method.
 * Could be inherited by the Manager and Assistant classes.
 */
public class Employee extends Person {
    private Company company;    
    private Office office;
    private UUID employeeId;     // The employee's ID number for the company.

    public Employee(Employee employee, Company company) {
        super(employee.getFirstName(), employee.getLastName(), employee.getSex(), employee.getAge());
        this.setEmployeeId(employee.getEmployeeId());
        this.setCompany(company);
    }

    public Employee(Person person, Company company) {
        super(person.getFirstName(), person.getLastName(), person.getSex(), person.getAge());
        this.setEmployeeId(UUID.randomUUID());
        this.setCompany(company);
    }

    public Employee(String firstName, String lastName, String sex, int age, Company company) {
        super(firstName, lastName, sex, age);
        this.setEmployeeId(UUID.randomUUID());
        this.setCompany(company);
    }

    @Override
    public String toString() {
        String appendix = (this.getOffice() != null) ? " and at office " + this.getOffice().getName() : "";
        return super.toString() + " works for " + this.getCompany().getName() + appendix;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Employee) {
            return this.getEmployeeId().equals(((Employee) obj).getEmployeeId());
        }
        return false;
    }

    public void worksFor() {
        System.out.printf("%s %s works for %s\n", this.getFirstName(), this.getLastName(), this.getCompany().getName());
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }
    public UUID getEmployeeId() {
        return this.employeeId;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    public Company getCompany() {
        return this.company;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
    public Office getOffice() {
        return this.office;
    }
}