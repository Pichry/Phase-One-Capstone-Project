package model;
import java.util.ArrayList;
import java.util.List;
public class Course  {
    private final String courseName;
    private final String courseCode;
    private final int credits;
    private final int studentCapacity;
    private final List<Student> enrolledStudents;

    public Course(String courseName, String courseCode, int credits, int studentCapacity) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
        this.studentCapacity = studentCapacity;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getCredits() {
        return credits;
    }

    public int getStudentCapacity() {
        return studentCapacity;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public int getEnrolledCount() {
        return enrolledStudents.size();
    }

    public boolean isFull() {
        return enrolledStudents.size() >= studentCapacity;
    }

    public void addStudent(Student student) {
        if (enrolledStudents.size() < studentCapacity && !enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
        }
    }

    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", credits=" + credits +
                ", enrolled=" + enrolledStudents.size() + "/" + studentCapacity +
                '}';
    }

}


