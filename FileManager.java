package service;

import model.Course;
import model.Instructor;
import model.Student;
import model.UniversityManager;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles file persistence for the university system
 * Saves and loads student, instructor, and course data
 */
public class FileManager {

    private static final String DATA_DIR = "data";
    private static final String STUDENTS_FILE = DATA_DIR + "/students.txt";
    private static final String COURSES_FILE = DATA_DIR + "/courses.txt";
    private static final String INSTRUCTORS_FILE = DATA_DIR + "/instructors.txt";
    private static final String ENROLLMENTS_FILE = DATA_DIR + "/enrollments.txt";

    public FileManager() {
        createDataDirectory();
    }

    private void createDataDirectory() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            if (dir.mkdir()) {
                System.out.println("Data directory created.");
            }
        }
    }


    public void saveStudents(List<Student> students) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(STUDENTS_FILE))) {
            for (Student student : students) {
                writer.println(formatStudent(student));
            }
            System.out.println("Students saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    public void saveCourses(List<Course> courses) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(COURSES_FILE))) {
            for (Course course : courses) {
                writer.println(formatCourse(course));
            }
            System.out.println("Courses saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving courses: " + e.getMessage());
        }
    }

    public void saveInstructors(List<Instructor> instructors) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(INSTRUCTORS_FILE))) {
            for (Instructor instructor : instructors) {
                writer.println(formatInstructor(instructor));
            }
            System.out.println("Instructors saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving instructors: " + e.getMessage());
        }
    }

    public void saveEnrollments(UniversityManager manager) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ENROLLMENTS_FILE))) {
            for (Student student : manager.getStudents()) {
                for (Course course : student.getEnrolledCourses().keySet()) {
                    Double grade = student.getEnrolledCourses().get(course);
                    writer.println(student.getStudentId() + "|" + course.getCourseCode() + "|" + 
                                 (grade != null ? grade : "null"));
                }
            }
            System.out.println("Enrollments saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving enrollments: " + e.getMessage());
        }
    }

    // ===== Load Operations =====

    public List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        File file = new File(STUDENTS_FILE);
        
        if (!file.exists()) {
            System.out.println("Students file not found. Starting fresh.");
            return students;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Student student = parseStudent(line);
                if (student != null) {
                    students.add(student);
                }
            }
            System.out.println("Loaded " + students.size() + " students from file.");
        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
        return students;
    }

    public List<Course> loadCourses() {
        List<Course> courses = new ArrayList<>();
        File file = new File(COURSES_FILE);
        
        if (!file.exists()) {
            System.out.println("Courses file not found. Starting fresh.");
            return courses;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Course course = parseCourse(line);
                if (course != null) {
                    courses.add(course);
                }
            }
            System.out.println("Loaded " + courses.size() + " courses from file.");
        } catch (IOException e) {
            System.out.println("Error loading courses: " + e.getMessage());
        }
        return courses;
    }

    public List<Instructor> loadInstructors() {
        List<Instructor> instructors = new ArrayList<>();
        File file = new File(INSTRUCTORS_FILE);
        
        if (!file.exists()) {
            System.out.println("Instructors file not found. Starting fresh.");
            return instructors;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Instructor instructor = parseInstructor(line);
                if (instructor != null) {
                    instructors.add(instructor);
                }
            }
            System.out.println("Loaded " + instructors.size() + " instructors from file.");
        } catch (IOException e) {
            System.out.println("Error loading instructors: " + e.getMessage());
        }
        return instructors;
    }

    // ===== Parsing Methods =====

    private String formatStudent(Student student) {
        return student.getStudentId() + "|" +
               student.getName() + "|" +
               student.getEmail() + "|" +
               student.getPhoneNumber() + "|" +
               student.getDepartment() + "|" +
               student.getGpa();
    }

    private Student parseStudent(String line) {
        try {
            String[] parts = line.split("\\|");
            if (parts.length >= 5) {
                Student student = new Student(parts[1], parts[2], parts[3], parts[4]);
                // Restore the saved ID if present
                if (parts.length > 0) {
                    student.setStudentId(parts[0]);
                }
                if (parts.length > 5) {
                    student.setGpa(Double.parseDouble(parts[5]));
                }
                return student;
            }
        } catch (Exception e) {
            System.out.println("Error parsing student line: " + e.getMessage());
        }
        return null;
    }

    private String formatCourse(Course course) {
        return course.getCourseCode() + "|" +
               course.getCourseName() + "|" +
               course.getCredits() + "|" +
               course.getStudentCapacity();
    }

    private Course parseCourse(String line) {
        try {
            String[] parts = line.split("\\|");
            if (parts.length >= 4) {
                return new Course(parts[1], parts[0], 
                                Integer.parseInt(parts[2]), 
                                Integer.parseInt(parts[3]));
            }
        } catch (Exception e) {
            System.out.println("Error parsing course line: " + e.getMessage());
        }
        return null;
    }

    private String formatInstructor(Instructor instructor) {
        return instructor.getInstructorId() + "|" +
               instructor.getName() + "|" +
               instructor.getEmail() + "|" +
               instructor.getPhoneNumber() + "|" +
               instructor.getDepartment();
    }

    private Instructor parseInstructor(String line) {
        try {
            String[] parts = line.split("\\|");
            if (parts.length >= 4) {
                return new Instructor(parts[1], parts[2], parts[3], 
                                     parts.length > 4 ? parts[4] : "Unknown");
            }
        } catch (Exception e) {
            System.out.println("Error parsing instructor line: " + e.getMessage());
        }
        return null;
    }

    // ===== Combined Save/Load =====

    public void saveAll(UniversityManager manager) {
        saveStudents(manager.getStudents());
        saveCourses(manager.getCourses());
        saveInstructors(manager.getInstructors());
        saveEnrollments(manager);
    }

    public void loadAll(UniversityManager manager) {
        for (Student student : loadStudents()) {
            manager.registerStudent(student);
        }
        for (Course course : loadCourses()) {
            manager.createCourse(course);
        }
        for (Instructor instructor : loadInstructors()) {
            manager.registerInstructor(instructor);
        }
        // After students and courses are registered, restore enrollments and grades
        loadEnrollments(manager);
    }

    private void loadEnrollments(UniversityManager manager) {
        File file = new File(ENROLLMENTS_FILE);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 2) {
                        String studentId = parts[0];
                        String courseCode = parts[1];
                        String gradeStr = parts.length > 2 ? parts[2] : "null";

                        try {
                            Student student = manager.getStudentById(studentId);
                            Course course = manager.getCourseByCode(courseCode);

                            // Enroll student in course (silently ignore business exceptions here)
                            try {
                                manager.enrollStudentInCourse(student, course);
                            } catch (Exception e) {
                                // Either course full or already enrolled; ignore during load
                            }

                            if (!"null".equalsIgnoreCase(gradeStr)) {
                                double grade = Double.parseDouble(gradeStr);
                                manager.assignGradeToStudent(student, course, grade);
                            }
                        } catch (Exception e) {
                            // Student or course not found -> ignore this enrollment record
                        }
                    }
                } catch (Exception inner) {
                    System.out.println("Error parsing enrollment line: " + inner.getMessage());
                }
            }
            System.out.println("Enrollments loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading enrollments: " + e.getMessage());
        }
    }
}
