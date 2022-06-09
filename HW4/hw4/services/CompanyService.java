package services;

import java.util.ArrayList;
import models.BoardMember;
import models.BoardOfDirectors;
import models.Company;
import models.Employee;
import models.Office;
import models.Person;

public class CompanyService {
    static boolean stdout = true;

    public CompanyService() {
        // Use default setup.
    }
    public CompanyService(boolean stdout) {
        CompanyService.stdout = stdout;
    }

   /**
     * Admit a new employee who with person object infromation to a company.
     * 
     * @param company
     * @param person
     */
    public static Employee admission(Company company, Person person) {
        if (stdout) {
            System.out.println("\n========== Before admission ==========");
            System.out.println(company.toString());
        }

        Employee newEmplyee = new Employee(person, company);
        company.getEmployees().add(newEmplyee);

        if (stdout) {
            System.out.println("========== After admission ==========");
            System.out.println(newEmplyee.toString());
            System.out.println(company.toString());
            System.out.println("========== End admission ==========\n");
        }
        return newEmplyee;
    }

    /**
     * Get employee from a company by Person object.
     * 
     * @param company
     * @param person
     * 
     * @return Employee object
     */
    public static Employee getEmployee(Company company, Person person) {
        for (Employee employee : company.getEmployees()) {
            if (employee.isEqual(person)) {
                if (stdout) {
                    System.out.println("\n========== Before getEmployee ==========");
                    System.out.println("Found employee: " + employee.toString());
                    System.out.println("========== After getEmployee ==========\n");
                }
                return employee;
            }
        }
        if (stdout) {
            System.out.println("No such employee.");
            System.out.println("========== After getEmployee ==========\n");
        }
        return null;
    }

    /**
     * Employee leaves the company.
     * 
     * @param employee
     */
    public static Person leaveCompany(Employee employee) {
        if (stdout) {
            System.out.println("\n========== Before leaveCompany ==========");
            System.out.println(employee.toString());
        }
        Company company = employee.getCompany();

        // Remove employee from company.
        company.getEmployees().remove(employee);

        // Remove company from employee.
        employee.setCompany(null);

        // Transform to person object.
        Person person = new Person(employee.getFirstName(), employee.getLastName(), employee.getSex(), employee.getAge());

        if (stdout) {
            System.out.println("========== After leaveCompany ==========");
            System.out.println(person.toString());
            System.out.println(company.toString());
            System.out.println("========== End leaveCompany ==========\n");
        }
        return person;
    }


    /**
     * Establish a new board member for a company.
     * 
     * @param company
     * @param boardMembers The board members to be added.
     */
    public static BoardOfDirectors establishBoardOfDirectors(Company company, ArrayList<Person> people) {
        if (stdout) {
            System.out.println("\n========== Before establishBoardOfDirectors ==========");
            System.out.println(company.toString());
        }

        // Check if the company already has a board of directors.
        if (company.getBoardOfDirectors() != null) {
            System.out.println("The company already has a board of directors.");
            return null;
        }

        // Check the new board of directors has enough members.
        if (people.size() < 3) {
            System.out.println("The new board of directors has not enough members.");
            return null;
        }

        // Check the new board of directors don't has too many members.
        if (people.size() > 8) {
            System.out.println("The new board of directors has too many members.");
            return null;
        }

        // Create a new board of directors.
        BoardOfDirectors newBoardOfDirectors = new BoardOfDirectors(company);

        // Add new board of directors to company.
        company.setBoardOfDirectors(newBoardOfDirectors);

        // Instantiate all new board members.
        ArrayList<BoardMember> boardMembers = new ArrayList<BoardMember>();
        for (Person person : people) {
            BoardMember boardMember = new BoardMember(person, newBoardOfDirectors);
            boardMembers.add(boardMember);
        }

        // Asign the new board members to the new board of directors.
        newBoardOfDirectors.setBoardMembers(boardMembers);

        if (stdout) {
            System.out.println("========== After establishBoardOfDirectors ==========");
            System.out.println(company.toString());
            System.out.println("========== End establishBoardOfDirectors ==========\n");
        }

        return newBoardOfDirectors;
    }

    /**
     * Add new board member to a company.
     * 
     * @param company
     * @param person
     */
    public static void addBoardMember(Company company, Person person) {
        if (stdout) {
            System.out.println("\n========== Before addBoardMembers ==========");
            System.out.println(company.toString());
        }

        // Check if the company has the board of directors yet.
        if (company.getBoardOfDirectors() == null) {
            System.out.println(company.getName() + "has no board of directors yet.");
            return;
        }

        // Check the new board of directors don't has too many members.
        if (company.getBoardOfDirectors().size() > 7) {
            System.out.println("The new board of directors has too many members.");
            return;
        }

        // Instantiate a new board member.
        BoardMember newBoardMember = new BoardMember(person, company.getBoardOfDirectors());

        // Add the new board member to the board of directors.
        company.getBoardOfDirectors().getBoardMembers().add(newBoardMember);

        if (stdout) {
            System.out.println("========== After addBoardMembers ==========");
            System.out.println(company.toString());
            System.out.println("========== End addBoardMembers ==========\n");
        }
    }

    /**
     * Remove board member from a company.
     * 
     * @param company
     * @param person
     */
    public static void removeBoardMember(Company company, Person person) {
        if (stdout) {
            System.out.println("\n========== Before removeBoardMembers ==========");
            System.out.println(company.toString());
        }

        // Check if the company has the board of directors yet.
        if (company.getBoardOfDirectors() == null) {
            System.out.println(company.getName() + "has no board of directors yet.");
            return;
        }

        // Check if the company has the enough board members.
        if (company.getBoardOfDirectors().size() < 4) {
            System.out.println(company.getName() + "hasn't enougth board member.");
            return;
        }

        BoardOfDirectors boardOfDirectors = company.getBoardOfDirectors();

        // Remove the board member.
        for (BoardMember boardMember : boardOfDirectors.getBoardMembers()) {
            // Get the target board member.
            if ((person).isEqual((Person) boardMember)) {
                // Remove target.
                boardOfDirectors.getBoardMembers().remove(boardMember);
                break;
            }
        }

        if (stdout) {
            System.out.println("========== After removeBoardMembers ==========");
            System.out.println(boardOfDirectors.toString());
            System.out.println("========== End removeBoardMembers ==========\n");
        }
    }

    /**
     * Remove board member from a company.
     * 
     * @param company
     * @param person
     */
    public static void changeBoardMember(Company company, Person currentMember, Person newMember) {
        if (stdout) {
            System.out.println("\n========== Before changeBoardMembers ==========");
            System.out.println(company.toString());
        }

        // Check if the company has the board of directors yet.
        if (company.getBoardOfDirectors() == null) {
            System.out.println(company.getName() + "has no board of directors yet.");
            return;
        }

        // Check the new board of directors don't has too many members.
        if (company.getBoardOfDirectors().size() > 7) {
            System.out.println("The new board of directors has too many members.");
            return;
        }

        BoardOfDirectors boardOfDirectors = company.getBoardOfDirectors();

        // Instantiate a new board member.
        BoardMember newboardMember = new BoardMember(newMember, company.getBoardOfDirectors());

        // Change the board member.
        for (BoardMember boardMember : company.getBoardOfDirectors().getBoardMembers()) {
            // Get the current board member.
            if (((Person)boardMember).isEqual(currentMember)) {
                // Remove old one.
                boardOfDirectors.getBoardMembers().remove(boardMember);
                // Add new one.
                boardOfDirectors.getBoardMembers().add(newboardMember);
                break;
            }
        }

        if (stdout) {
            System.out.println("========== After changeBoardMember ==========");
            System.out.println(boardOfDirectors.toString());
            System.out.println("========== End changeBoardMember ==========\n");
        }
    }

    /**
     * Establish a new office.
     * 
     * @param company
     * @param name
     */
    public static Office establishOffice(Company company, String name) {
        if (stdout) {
            System.out.println("\n========== Before establishOffice ==========");
            System.out.println(company.toString());
        }
        // Check if the company already has a same name office.
        for (Office office : company.getOffices()) {
            if (office.getName().equals(name)) {
                System.out.println("The company already has a same name office.");
                return null;
            }
        }

        // Instantiate a new office.
        Office office = new Office(company, name);

        // Asign the new office to the company.
        company.getOffices().add(office);

        if (stdout) {
            System.out.println("========== After establishOffice ==========");
            System.out.println(company.toString());
            System.out.println(office.toString());
            System.out.println("========== End establishOffice ==========\n");
        }

        return office;
    }
}