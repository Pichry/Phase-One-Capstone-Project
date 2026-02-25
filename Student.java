package model;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Student extends Person {

    private String studentId;
    private String department;
    private double gpa;
    private Map<Course, Double> enrolledCourses;

    public Student(String name, String email, String phoneNumber, String department) {
        super(name, email, phoneNumber);
        this.studentId = UUID.randomUUID().toString();
        this.department = department;
        this.gpa = 0.0;
        this.enrolledCourses = new HashMap<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public Map<Course, Double> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollCourse(Course course) {
        if (enrolledCourses == null) {
            enrolledCourses = new HashMap<>();
        }
        enrolledCourses.put(course, null);
    }

    public void addGrades(Course course, double grade) {
        if (enrolledCourses != null && enrolledCourses.containsKey(course)) {
            enrolledCourses.put(course, grade);
        } else {
            System.out.println("Student is not enrolled in the course: " + course.getCourseName());
        }
    }

    public void calculateGPA() {
        if (enrolledCourses != null && !enrolledCourses.isEmpty()) {
            double totalGrades = 0.0;
            int count = 0;
            for (Double grade : enrolledCourses.values()) {
                if (grade != null) {
                    totalGrades += grade;
                    count++;
                }
            }
            if (count > 0) {
                this.gpa = totalGrades / count;
            }
        }
    }

    public void dropCourse(Course course) {
        if (enrolledCourses != null && enrolledCourses.containsKey(course)) {
            enrolledCourses.remove(course);
        } else {
            System.out.println("Student is not enrolled in the course: " + course.getCourseName());
        }
    }

    public void displayStudentInfo() {
        System.out.println("\n========== Student Information ==========");
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Department: " + department);
        System.out.println("GPA: " + String.format("%.2f", gpa));

        if (enrolledCourses != null && !enrolledCourses.isEmpty()) {
            System.out.println("\nEnrolled Courses:");
            for (Map.Entry<Course, Double> entry : enrolledCourses.entrySet()) {
                Course course = entry.getKey();
                Double grade = entry.getValue();
                System.out.println("  - " + course.getCourseName() + " (" + course.getCourseCode() + "): " +
                    (grade != null ? String.format("%.2f", grade) : "No grade yet"));
            }
        } else {
            System.out.println("No enrolled courses.");
        }
        System.out.println("==========================================\n");
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + getName() + '\'' +
                ", department='" + department + '\'' +
                ", gpa=" + gpa +
                ", enrolledCourses=" + enrolledCourses.size() +
                '}';
    }
}