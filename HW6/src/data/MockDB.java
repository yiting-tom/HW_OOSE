package data;

import java.util.ArrayList;

import models.course.Course;
import models.users.Admin;
import models.users.Student;
import models.users.User;
import services.StudentService;

/**
 * This class is used to store all the mock data of the application.
 * You could replace it by a real database or a file.
 */
public class MockDB {
    public ArrayList<User> users = new ArrayList<User>(){
        {
            // Add student users.
            add(new Student(0, "Alice", "P76100000", "abc123"));
            add(new Student(1, "Bob", "P76100001", "abc123"));

            // Add admin user.
            add(new Admin(0, "Charlie", "admin", "admin"));
        }
    };

    public ArrayList<Course> courses = new ArrayList<Course>(){
        {
            try {
                // Add some classes.
                add(new Course("Object-Oriented Software Engineering", " 1102_P752000", 20));
                add(new Course("Algorithms", " 1102_F721300", 69));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    };

    public MockDB() {
        Student alice = (Student) users.get(0);
        Course oose = courses.get(0);
        Course algo = courses.get(1);

        // Set algorithms as pre-requests of oose.
        oose.setPreRequests(new ArrayList<>(){{add(algo);}});

        // Set Alice passed algorithms.
        StudentService.passCourse(alice, algo);
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public Course getCourseByCode(String code) {
        for (Course course : courses) {
            if (course.getCode().equals(code)) {
                return course;
            }
        }
        return null;
    }


    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Student) {
                students.add((Student) user);
            }
        }
        return students;
    }

    public String getStudentList() {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        for (Student student : getStudents()) {
            sb.append(++idx + ' ').append(student.getUsername()).append(" ").append(student.getName()).append("\n");
        }
        return sb.toString();
    }

    public Student getStudentByUsername(String username) {
        for (Student student : getStudents()) {
            if (student.getUsername().equals(username)) {
                return student;
            }
        }
        return null;
    }
}
