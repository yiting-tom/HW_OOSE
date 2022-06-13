package models.exceptions;

public class IsFullException extends CourseException {
    /**
     * Constructs an {@code IsFullException} with the specified detail message.
     */
    public IsFullException() {
        super();
    }

    /**
     * Constructs an {@code IsFullException} with the specified detail message.
     * 
     * @param message The detail message.
     */
    public IsFullException(String message) {
        super(message);
    }

    /**
     * Constructs an {@code IsFullException} with the specified detail message
     * and cause.
     *
     * <p> Note that the detail message associated with {@code cause} is
     * <i>not</i> automatically incorporated into this exception's detail
     * message.
     *
     * @param message
     *        The detail message (which is saved for later retrieval
     *        by the {@link #getMessage()} method)
     *
     * @param cause
     *        The cause (which is saved for later retrieval by the
     *        {@link #getCause()} method).  (A null value is permitted,
     *        and indicates that the cause is nonexistent or unknown.)
     */
    public IsFullException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an {@code IsFullException} with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of {@code cause}).
     * This constructor is useful for IsFull exceptions that are little more
     * than wrappers for other throwables.
     *
     * @param cause
     *        The cause (which is saved for later retrieval by the
     *        {@link #getCause()} method).  (A null value is permitted,
     *        and indicates that the cause is nonexistent or unknown.)
     */
    public IsFullException(Throwable cause) {
        super(cause);
    }
}
