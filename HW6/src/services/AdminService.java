package services;

import models.course.Course;
import models.exceptions.AlreadyTakenException;
import models.exceptions.CourseException;
import models.exceptions.IsFullException;
import models.users.Student;

/**
 * The service for Admin.
 */
public class AdminService {
    /**
     * Make a student to take a course.
     * 
     * @param student The student.
     * @param course The course.
     * @throws CourseException If the course is full.
     * @throws CourseException If the student has already registered.
     */
    public synchronized static void takeCourse(Student student, Course course) throws CourseException {
        if (course.getMaxStudents() <= course.getStudents().size()) {
            throw new IsFullException(
                "The course have been fully taken:\n" +
                "--------------------\n" + 
                course.getInfoString() + "\n" +
                "--------------------\n"
            );
        }
        if (student.getRegister().contains(course)) {
            throw new AlreadyTakenException(
                "The student has already registered:\n" +
                "--------------------\n" + 
                student.getInfoString() + "\n" +
                "--------------------\n"
            );
        }
        if (student.getPassed().contains(course)) {
            throw new AlreadyTakenException(
                "The student has already passed:\n" +
                "--------------------\n" + 
                student.getInfoString() + "\n" +
                "--------------------\n"
            );
        }
        // without pre-requests
        student.getRegister().add(course);
        course.getStudents().add(student);
    }
}
