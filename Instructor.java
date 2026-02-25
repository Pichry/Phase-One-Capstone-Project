package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Instructor extends Person {

    private String instructorId;
    private String department;
    private List<Course> coursesTaught;

    public Instructor(String name, String email, String phoneNumber, String department) {
        super(name, email, phoneNumber);
        this.instructorId = UUID.randomUUID().toString();
        this.department = department;
        this.coursesTaught = new ArrayList<>();
    }

    public String getInstructorId() {
        return instructorId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Course> getCoursesTaught() {
        return coursesTaught;
    }

    public void assignCourse(Course course) {
        if (!coursesTaught.contains(course)) {
            coursesTaught.add(course);
        } else {
            System.out.println("Course already assigned to instructor.");
        }
    }

    public void removeCourse(Course course) {
        if (coursesTaught != null && coursesTaught.contains(course)) {
            coursesTaught.remove(course);
        } else {
            System.out.println("Course not found in instructor's list.");
        }
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "instructorId='" + instructorId + '\'' +
                ", name='" + getName() + '\'' +
                ", department='" + department + '\'' +
                ", coursesTaught=" + coursesTaught.size() +
                '}';
    }
}
