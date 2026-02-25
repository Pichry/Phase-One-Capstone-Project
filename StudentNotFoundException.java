package exception;

/**
 * Exception thrown when a student cannot be found
 */
public class StudentNotFoundException extends Exception {

    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

