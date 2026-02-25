package exception;

/**
 * Exception thrown when a course cannot be found
 */
public class CourseNotFoundException extends Exception {

    public CourseNotFoundException(String message) {
        super(message);
    }

    public CourseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

