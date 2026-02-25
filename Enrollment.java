package model;

import java.util.UUID;

class Enrollment {

    private final String enrollmentId;
    private final Student student;
    private final Course course;
    private final Double grade;


    public Enrollment(Student student, Course course) {
        this.enrollmentId = UUID.randomUUID().toString();
        this.student = student;
        this.course = course;
        this.grade = null;

    }

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {

    }
public void setName(String name) {

    }


    @Override
    public String toString() {
        return "Enrollment{" +
                "enrollmentId='" + enrollmentId + '\'' +
                ", student=" + student.getName() +
                ", course=" + course.getCourseName() +
                ", grade=" + grade +
                '}';
    }
}
