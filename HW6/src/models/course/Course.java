package models.course;

import java.util.ArrayList;

import models.users.Student;

/**
 * The Course class.
 */
public class Course {
    private ArrayList<String> allCourses = new ArrayList<String>();
    private ArrayList<Course> preRequests = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    private String name;
    private String code;
    private int maxStudents;

    /**
     * The constructor of Course.
     * 
     * @param name The name of the course.
     * @param code The code of the course.
     * @param maxStudents The maximum number of students in the course.
     * @throws IllegalAccessException If the course code is already in use.
     */
    public Course(String name, String code, int maxStudents) throws IllegalAccessException {
        this.setName(name);
        this.setCode(code);
        this.setMaxStudents(maxStudents);
    }

    /**
     * The constructor of Course.
     * 
     * @param name The name of the course.
     * @param code The code of the course.
     * @param maxStudents The maximum number of students in the course.
     * @param preRequests The pre-requests of the course.
     * @throws IllegalAccessException If the course code is already in use.
     */
    public Course(String name, String code, int maxStudents, ArrayList<Course> preRequests) throws IllegalAccessException {
        this(name, code, maxStudents);
        setPreRequests(preRequests);
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", capacity=" + capacityString() +
                '}';
    }

    /**
     * Return a formatted string of the course information.
     * 
     * @return A formatted string.
     */
    public String getInfoString() {
        return "name: " + name +
               "\ncode: " + code +
               "\ncapacity: " + capacityString() +
               "\npreRequests: " + getCourseString(preRequests);
    }

    /**
     * Return all courses' names.
     * 
     * @param courses The courses.
     * @return A formatted string.
     */
    public static String getCourseString(ArrayList<Course> courses) {
        ArrayList<String> response = new ArrayList<>();
        for (Course course: courses) {
            response.add(course.getName());
        }
        return String.join(", ", response);
    }

    /**
     * Return a formatted string of the courses' information.
     * 
     * @param courses The courses.
     * @return A formatted string.
     */
    public static String getCourseListString(ArrayList<Course> courses) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%6s %8s %13s %s\n", "Select", "Capacity", "Code", "Name"));
        for (int i=0; i<courses.size(); i++) {
            Course course = courses.get(i);
            sb.append(String.format("  [%2d] %8s %13s %s\n", i, course.capacityString(), course.getCode(), course.getName()));
        }
        return sb.toString();
    }

    /**
     * Return a formatted string of the course capacity.
     * 
     * @return The formatted string.
     */
    public String capacityString() {
        return students.size() + "/" + maxStudents;
    }

    public ArrayList<Course> getPreRequests() {
        return preRequests;
    }
    public void setPreRequests(ArrayList<Course> preRequests) {
        this.preRequests = preRequests;
    }
    public ArrayList<Student> getStudents() {
        return students;
    }
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) throws IllegalAccessException {
        // Check if the course code is valid.
        if (allCourses.contains(code)) {
            throw new IllegalArgumentException("Course code already exists.");
        }
        this.code = code;
    }
    public int getMaxStudents() {
        return maxStudents;
    }
    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }
}
