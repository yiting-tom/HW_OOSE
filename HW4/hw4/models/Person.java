package models;

/**
 * The Person object.
 * 
 * Stores the person's basic information, and be inherited by the Employee class.
 */
public class Person {
    private String firstName;
    private String lastName;
    private String sex;
    private int age;

    public Person(String firstName, String lastName, String sex, int age) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setSex(sex);
        this.setAge(age);
    }

    @Override
    public String toString() {
        return "<" + this.getClass().getName() + ": " + this.getFirstName() + " " + this.getLastName() + ">";
    }

    public boolean isEqual(Person person) {
        return this.getFirstName().equals(person.getFirstName()) && this.getLastName().equals(person.getLastName()) && this.getSex().equals(person.getSex()) && this.getAge() == person.getAge();
    }

    public String getName() {
        return this.getFirstName() + " " + this.getLastName();
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return this.lastName;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getSex() {
        return this.sex;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return this.age;
    }
}