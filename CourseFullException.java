package exception;

/**
 * Exception thrown when attempting to enroll a student in a full course
 */
public class CourseFullException extends Exception {

    public CourseFullException(String message) {
        super(message);
    }

    public CourseFullException(String message, Throwable cause) {
        super(message, cause);
    }
}

