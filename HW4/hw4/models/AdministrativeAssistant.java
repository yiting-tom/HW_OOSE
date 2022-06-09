package models;
import models.Assistant;

public class AdministrativeAssistant extends Assistant {
    public AdministrativeAssistant(Employee employee) {
        super(employee, "administrative");
    }
}