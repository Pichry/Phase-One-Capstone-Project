package service;
import java.util.Scanner;
import model.Course;
import model.Instructor;
import model.Student;
import model.UniversityManager;
import exception.*;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private final UniversityManager manager;
    private final FileManager fileManager;
    private final Scanner scanner;

    public ConsoleUI() {
        this.manager = new UniversityManager();
        this.fileManager = new FileManager();
        this.scanner = new Scanner(System.in);


        fileManager.loadAll(manager);
    }

    public void start() {
        System.out.println("\n========================================");
        System.out.println("  Academic Enrollment Management System");
        System.out.println("========================================\n");

        boolean running = true;
        while (running) {
            displayMainMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    registerStudentMenu();
                    break;
                case "2":
                    createCourseMenu();
                    break;
                case "3":
                    enrollStudentMenu();
                    break;
                case "4":
                    viewStudentRecordMenu();
                    break;
                case "5":
                    assignGradeMenu();
                    break;
                case "6":
                    dropCourseMenu();
                    break;
                case "7":
                    viewDeansList();
                    break;
                case "8":
                    viewAllStudents();
                    break;
                case "9":
                    viewAllCourses();
                    break;
                case "10":
                    registerInstructorMenu();
                    break;
                case "11":
                    saveAndExit();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\n===== Main Menu =====");
        System.out.println("1. Register Student");
        System.out.println("2. Create Course");
        System.out.println("3. Enroll Student in Course");
        System.out.println("4. View Student Record");
        System.out.println("5. Assign Grade");
        System.out.println("6. Drop Course");
        System.out.println("7. View Dean's List (GPA > 3.5)");
        System.out.println("8. View All Students");
        System.out.println("9. View All Courses");
        System.out.println("10. Register Instructor");
        System.out.println("11. Save and Exit");
        System.out.print("\nEnter your choice: ");
    }

    // ===== Student Registration =====
    private void registerStudentMenu() {
        System.out.println("\n===== Register Student =====");
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine().trim();

        System.out.print("Enter department: ");
        String department = scanner.nextLine().trim();

        Student student = new Student(name, email, phone, department);
        manager.registerStudent(student);
        System.out.println("\nStudent registered successfully!");
        System.out.println("Student ID: " + student.getStudentId());
    }

    // ===== Course Creation =====
    private void createCourseMenu() {
        System.out.println("\n===== Create Course =====");
        System.out.print("Enter course name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter course code: ");
        String code = scanner.nextLine().trim();

        System.out.print("Enter credits: ");
        int credits = getIntInput();

        System.out.print("Enter student capacity: ");
        int capacity = getIntInput();

        Course course = new Course(name, code, credits, capacity);
        manager.createCourse(course);
        System.out.println("\nCourse created successfully!");
    }

    // ===== Enrollment =====
    private void enrollStudentMenu() {
        System.out.println("\n===== Enroll Student in Course =====");
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine().trim();

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine().trim();

        try {
            Student student = manager.getStudentById(studentId);
            Course course = manager.getCourseByCode(courseCode);
            manager.enrollStudentInCourse(student, course);
            System.out.println("\nEnrollment successful!");
        } catch (StudentNotFoundException e) {
            System.out.println("\nError: " + e.getMessage());
        } catch (CourseNotFoundException e) {
            System.out.println("\nError: " + e.getMessage());
        } catch (CourseFullException e) {
            System.out.println("\nError: " + e.getMessage());
        } catch (StudentAlreadyEnrolledException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    // ===== View Student Record =====
    private void viewStudentRecordMenu() {
        System.out.println("\n===== View Student Record =====");
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine().trim();

        try {
            Student student = manager.getStudentById(studentId);
            student.displayStudentInfo();
        } catch (StudentNotFoundException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    // ===== Assign Grade =====
    private void assignGradeMenu() {
        System.out.println("\n===== Assign Grade =====");
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine().trim();

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine().trim();

        System.out.print("Enter grade (0-100): ");
        double grade = getDoubleInput();

        try {
            Student student = manager.getStudentById(studentId);
            Course course = manager.getCourseByCode(courseCode);

            if (!student.getEnrolledCourses().containsKey(course)) {
                System.out.println("\nError: Student is not enrolled in this course.");
                return;
            }

            manager.assignGradeToStudent(student, course, grade);
            System.out.println("\nGrade assigned successfully!");
        } catch (StudentNotFoundException e) {
            System.out.println("\nError: " + e.getMessage());
        } catch (CourseNotFoundException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    // ===== Drop Course =====
    private void dropCourseMenu() {
        System.out.println("\n===== Drop Course =====");
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine().trim();

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine().trim();

        try {
            Student student = manager.getStudentById(studentId);
            Course course = manager.getCourseByCode(courseCode);
            manager.dropCourseForStudent(student, course);
            System.out.println("\nCourse dropped successfully!");
        } catch (StudentNotFoundException e) {
            System.out.println("\nError: " + e.getMessage());
        } catch (CourseNotFoundException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    // ===== View Dean's List =====
    private void viewDeansList() {
        System.out.println("\n===== Dean's List (GPA > 3.5) =====");
        List<Student> deansList = manager.getDeansList();

        if (deansList.isEmpty()) {
            System.out.println("No students on Dean's List.");
            return;
        }

        System.out.println("Students on Dean's List:");
        for (Student student : deansList) {
            System.out.println("  - " + student.getName() +
                             " (ID: " + student.getStudentId() +
                             ", GPA: " + String.format("%.2f", student.getGpa()) + ")");
        }
    }

    // ===== View All Students =====
    private void viewAllStudents() {
        manager.displayAllStudents();
    }

    // ===== View All Courses =====
    private void viewAllCourses() {
        manager.displayAllCourses();
    }

    // ===== Register Instructor =====
    private void registerInstructorMenu() {
        System.out.println("\n===== Register Instructor =====");
        System.out.print("Enter instructor name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine().trim();

        System.out.print("Enter department: ");
        String department = scanner.nextLine().trim();

        Instructor instructor = new Instructor(name, email, phone, department);
        manager.registerInstructor(instructor);
        System.out.println("\nInstructor registered successfully!");
        System.out.println("Instructor ID: " + instructor.getInstructorId());
    }

    // ===== Save and Exit =====
    private void saveAndExit() {
        System.out.println("\n===== Saving Data =====");
        fileManager.saveAll(manager);
        System.out.println("\nThank you for using the Academic Enrollment Management System!");
        System.out.println("Goodbye!");
        scanner.close();
    }

    // ===== Helper Methods =====
    private int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return getIntInput();
        }
    }

    private double getDoubleInput() {
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return getDoubleInput();
        }
    }
}

