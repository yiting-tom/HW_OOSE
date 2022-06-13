package models.users;

import java.util.ArrayList;

import models.course.Course;
import models.roles.StudentRole;

/**
 * The Student class.
 */
public class Student extends User implements StudentRole {
    // The courses that the student has registered.
    private ArrayList<Course> register = new ArrayList<>();

    // The courses that the student has passed.
    private ArrayList<Course> passed = new ArrayList<>();

    /**
     * The constructor of Student.
     * 
     * @param id The id of the student.
     * @param name The name of the student.
     * @param username The username of the student.
     * @param password The password of the student.
     */
    public Student(int id, String name, String username, String password) {
        super(id, name, username, password, 0);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name=" + getName() +
                ", registered=" + Course.getCourseString(register) +
                ", passed=" + Course.getCourseString(passed) +
                '}';
    }

    /**
     * Return a formatted string of the student information.
     * 
     * @return A formatted string.
     */
    public String getInfoString() {
        return "name: " + getName() +
               "\nusername: " + getUsername() +
               "\ntaken courses: " + Course.getCourseString(register) +
               "\npassed courses: " + Course.getCourseString(passed);
    }

    /**
     * Return a formatted string of the students' information.
     * 
     * @param students The students.
     * @return The formatted string.
     */
    public static String getStudentLiString(ArrayList<Student> students) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%6s %10s %s\n", "Select", "Username", "Name"));

        for (int i=0; i<students.size(); i++) {
            Student student = students.get(i);
            sb.append(String.format("  [%2d] %10s %s\n", i, student.getUsername(), student.getName()));
        }

        return sb.toString();
    }

    public ArrayList<Course> getPassed() {
        return passed;
    }

    public void setPassed(ArrayList<Course> passed) {
        this.passed = passed;
    }

    public ArrayList<Course> getRegister() {
        return register;
    }

    public void setRegister(ArrayList<Course> register) {
        this.register = register;
    }
}
