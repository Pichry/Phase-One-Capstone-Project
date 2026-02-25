package exception;

/**
 * Exception thrown when a student is already enrolled in a course
 */
public class StudentAlreadyEnrolledException extends Exception {

    public StudentAlreadyEnrolledException(String message) {
        super(message);
    }

    public StudentAlreadyEnrolledException(String message, Throwable cause) {
        super(message, cause);
    }
}

