package services;

import models.course.Course;
import models.exceptions.AlreadyTakenException;
import models.exceptions.CourseException;
import models.exceptions.IsFullException;
import models.exceptions.NotPassedException;
import models.users.Student;

/**
 * The service for student.
 */
public class StudentService {
    /**
     * Take a course.
     * 
     * @param student The student.
     * @param course The course.
     * @throws CourseException If the course is full.
     * @throws CourseException If the student has already registered.
     * @throws CourseException If the student has not passed the pre-requests.
     */
    public static void takeCourse(Student student, Course course) throws CourseException {
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
        if (!checkPreRequests(student, course) ) {
            throw new NotPassedException(
                "The student has not passed the pre-requests:\n" +
                "--------------------\n" + 
                student.getInfoString() + "\n" +
                "--------------------\n" + 
                course.getInfoString() + "\n" +
                "--------------------\n"
            );
        }
        student.getRegister().add(course);
        course.getStudents().add(student);
    }

    /**
     * Check if the student has passed the pre-requests.
     * 
     * @param student The student.
     * @param course The course.
     * @return True if the student has passed the pre-requests.
     */
    public static boolean checkPreRequests(Student student, Course course) {
        for (Course preRequest : course.getPreRequests()) {
            if (!student.getPassed().contains(preRequest)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Pass a course.
     * 
     * @param student The student.
     * @param course The course.
     */
    public static void passCourse(Student student, Course course) {
        // We don't need to check if the student has taken the course.
        // We check at {@code StudentService.takeCourse} already.
        student.getPassed().add(course);
    }
}
