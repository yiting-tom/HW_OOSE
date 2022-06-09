import java.util.ArrayList;
import models.AdministrativeAssistant;
import models.AdministrativeAssistant;
import models.Company;
import models.Employee;
import models.Manager;
import models.Office;
import models.Person;
import services.CompanyService;
import services.WorkService;

class main {

    public static void main(String[] args) {
        Person person1 = new Person("Tom"       , "Lee"     , "male", 23);
        Person person2 = new Person("Aiden"     , "Yeh"     , "male", 25);
        Person person3 = new Person("Will"      , "Lin"     , "male", 23);

        Company company1 = new Company("Google");

        // Company1 admit person1.
        Employee employee1 = CompanyService.admission(company1, person1);
        // Company1 admit person2.
        Employee employee2 = CompanyService.admission(company1, person2);

        // Get person1 from company1 in Employee object.
        employee1 = CompanyService.getEmployee(company1, person1);

        // Company1 establish a new office named office1_1
        Office office1_1 = CompanyService.establishOffice(company1, "office1_1");
        // Company1 establish a new office named office1_2
        Office office1_2 = CompanyService.establishOffice(company1, "office1_2");

        // Allocate person1 to office1_1
        WorkService.allocateOfficeTo(office1_1, employee1);
        WorkService.allocateOfficeTo(office1_2, employee2);

        // Employee1 promote to manager.
        Manager manager1 = WorkService.assignToManager(employee1);

        // Employee1 promote to administrative assistant.
        AdministrativeAssistant admin_assistant1 = (AdministrativeAssistant)WorkService.assignToAssistant(employee2, "administrative");

        // Assign administrative assistant admin_assistant1 to manager manager1.
        WorkService.assignAdministrtiveAssistantToManager(manager1, admin_assistant1);

        // Person1 leave company1.
        person1 = CompanyService.leaveCompany(employee1);



        Person personA = new Person("Sundar"    , "Pichai"  , "male", 49);
        Person personB = new Person("Larry"     , "Page"    , "male", 49);
        Person personC = new Person("Sergey"    , "Brin"    , "male", 48);
        Person personD = new Person("Frances"   , "Arnold"  , "male", 65);
        Person personE = new Person("Eric"      , "Schmidt" , "male", 67);

        // Company1 establish a new board of directors with members personA, personB, personC.
        CompanyService.establishBoardOfDirectors(company1, new ArrayList<Person>(){{
            add(personA);
            add(personB);
            add(personC);
        }});

        // Change company1's board member personC to personD.
        CompanyService.changeBoardMember(company1, personC, personD);

        // Add personE to company1's board of directors.
        CompanyService.addBoardMember(company1, personE);

        // Remove personE from company1's board of directors.
        CompanyService.removeBoardMember(company1, personE);
    }
}