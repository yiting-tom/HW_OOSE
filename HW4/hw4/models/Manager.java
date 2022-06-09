package models;
import java.util.ArrayList;
import models.AdministrativeAssistant;
import models.Employee;

public class Manager extends Employee {
    private ArrayList<AdministrativeAssistant> administrativeAssistants = new ArrayList<AdministrativeAssistant>();


    public Manager(Employee employee) {
        super(employee, employee.getCompany());
    }

    public Manager(Employee employee, ArrayList<AdministrativeAssistant> administrativeAssistants) {
        this(employee);
        this.setAdministrativeAssistants(administrativeAssistants);
    }

    @Override
    public String toString() {
        return "<" + this.getClass().getName() + ": " + this.getName() + "> " + " has " + this.getAdministrativeAssistants().size() + " administrative assistants";
    }

    public void setAdministrativeAssistants(ArrayList<AdministrativeAssistant> administrativeAssistants) {
        this.administrativeAssistants = administrativeAssistants;
    }
    public ArrayList<AdministrativeAssistant> getAdministrativeAssistants() {
        return this.administrativeAssistants;
    }
}