package services;
import models.AdministrativeAssistant;
import models.Assistant;
import models.Company;
import models.Employee;
import models.Manager;
import models.Office;


public class WorkService {
    static boolean stdout = true; 

    /**
     * Change employee.
     * 
     * @param currentEmployee
     * @param newPosition
     */
    protected static void changePosition(Employee currentEmployee, Employee newEmployee) {
        if (stdout) {
            System.out.println("\n========== Before changePosition ==========");
            System.out.println("Current Position: " + currentEmployee.toString());
        }


        // Check if these are in same company.
        if (!currentEmployee.getCompany().equals(newEmployee.getCompany())) {
            throw new IllegalArgumentException("Employees must be in the same company");
        }
        Company company = currentEmployee.getCompany();

        // Remove the employee from the company.
        company.getEmployees().remove(currentEmployee);

        // Add the manager into company.
        company.getEmployees().add(newEmployee);

        if (stdout) {
            System.out.println("========== After changePosition ==========");
            System.out.println("New Position: " + newEmployee.toString());
            System.out.println("========== End changePosition ==========\n");
        }
    }

    /**
     * assigns an employee to a manager.
     * 
     * @param employee The employee to be assigned.
     * 
     * @return The manager that the employee is assigned to.
     */
    public static Manager assignToManager(Employee employee) {
        if (stdout) {
            System.out.println("\n========== Before assignToManager ==========");
            System.out.println(employee.toString());
        }

        Manager manager = new Manager(employee);

        // Change position of the employee.
        changePosition(employee, manager);

        if (stdout) {
            System.out.println("========== After assignToManager ==========");
            System.out.println(manager.toString());
            System.out.println("========== End assignToManager ==========\n");
        }

        return manager;
    }

    /**
     * assigns an employee to a assitant.
     * 
     * @param employee The employee to be assigned.
     * @param type The type of the assistant.
     * 
     * @return The assistant that the employee is assigned to.
     */
    public static Assistant assignToAssistant(Employee employee, String type) {
        if (stdout) {
            System.out.println("\n========== Before assignToAssistant ==========");
            System.out.println(employee.toString());
        }

        // Check if the type is valid.
        if (type == "administrative") {
            // Instantiate the assistant.
            AdministrativeAssistant assistant = new AdministrativeAssistant(employee);

            // Change position of the employee.
            changePosition(employee, assistant);

            if (stdout) {
                System.out.println("========== After assignToAssistant ==========");
                System.out.println(assistant.toString());
                System.out.println("========== End assignToAssistant ==========\n");
            }
            return assistant;
        }
        //***********************************************
        // Can assign to other types of assistants here.
        //***********************************************


        throw new IllegalArgumentException("The type is not valid");
    }

    /**
     * Assigns an administrative assistant to a manager.
     * 
     * @param manager
     * @param assistant
     */
    public static void assignAdministrtiveAssistantToManager(Manager manager, AdministrativeAssistant assistant) {
        if (stdout) {
            System.out.println("\n========== Before assignAdministrtiveAssistantToManager ==========");
            System.out.println(manager.toString());
            System.out.println(assistant.toString());
        }
        // Check if the manager and the assistant are in the same company.
        if (!manager.getCompany().equals(assistant.getCompany())) {
            throw new IllegalArgumentException("The manager and the assistant must be in the same company");
        }

        // Check if the assistant is already assigned to the manager.
        for (AdministrativeAssistant admin_assistant : manager.getAdministrativeAssistants()) {
            if (admin_assistant.equals(assistant)) {
                throw new IllegalArgumentException("The assistant is already assigned to the manager");
            }
        }

        manager.getAdministrativeAssistants().add(assistant);
        assistant.getSupervisors().add(manager);

        if (stdout) {
            System.out.println("========== After assignAdministrtiveAssistantToManager ==========");
            System.out.println(manager.toString());
            System.out.println(assistant.toString());
            System.out.println("========== End assignAdministrtiveAssistantToManager ==========\n");
        }
    }

    /**
     * Allocate an office to employee.
     * 
     * @param office The office to be allocated.
     * @param employee The employee to be allocated.
     */
    public static void allocateOfficeTo(Office office, Employee employee) {
        if (stdout) {
            System.out.println("\n========== Before allocateOfficeTo ==========");
            System.out.println(office.toString());
            System.out.println(employee.toString());
        }

        // Check if these are in same company.
        if (!office.getCompany().equals(employee.getCompany())) {
            throw new IllegalArgumentException("Employee and office must be in the same company");
        }

        // Check if the employee is already in an office.
        if (employee.getOffice() != null && employee.getOffice().equals(office)) {
            System.out.println("Employee is already in an office");
        }

        // Add the employee to the office.
        office.getEmployees().add(employee);

        // Set the employee's office.
        employee.setOffice(office);
        if (stdout) {
            System.out.println("========== After allocateOfficeTo ==========");
            System.out.println(office.toString());
            System.out.println(employee.toString());
            System.out.println("========== End allocateOfficeTo ==========\n");
        }
    }


}