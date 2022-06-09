package models;
import java.util.ArrayList;
import models.Employee;

/**
 * The Assistant abstract class.
 * 
 * Only the AdministrativeAssistant classes are instantiable.
 */
public abstract class Assistant extends Employee {
    private String type;
    private ArrayList<Manager> supervisors = new ArrayList<Manager>();

    public Assistant(Employee employee, String type) {
        super(employee, employee.getCompany());
        this.setType(type);
    }

    public Assistant(Employee employee, String type, ArrayList<Manager> supervisors) {
        super(employee, employee.getCompany());
        this.setType(type);
        this.setSupervisors(supervisors);
    }

    @Override
    public String toString() {
        return "<" + this.getClass().getName() + ": " + this.getName() + "> " + " is a " + this.getType() +" assistant and supervisors are " + this.getSupervisors().toString();
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return this.type;
    }

    public void setSupervisors(ArrayList<Manager> supervisors) {
        this.supervisors = supervisors;
    }
    public ArrayList<Manager> getSupervisors() {
        return this.supervisors;
    }
}