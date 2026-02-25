# Academic Enrollment Management System

A comprehensive Java-based application for managing student enrollments, course registrations, and academic records in a university setting.

## Overview

This system implements Object-Oriented Programming (OOP) principles and Java Collections to manage the complex relationships between students, instructors, and courses. It features custom exception handling, file persistence, and a robust console-based user interface.

## Features

### Core Functionality
- **Student Management**: Register students with automatic ID generation
- **Course Management**: Create courses with capacity management
- **Enrollment System**: Enroll students in courses with automatic conflict detection
- **Grade Tracking**: Assign and track student grades with automatic GPA calculation
- **Course Dropping**: Allow students to drop courses
- **Dean's List**: Generate a list of high-performing students (GPA > 3.5)
- **Instructor Management**: Register instructors and assign them to courses

### Technical Features
- **OOP Design**: Abstract Person class with Student and Instructor implementations
- **Collections Management**: Uses HashMap, ArrayList, and List for complex data relationships
- **Custom Exceptions**: Handles business logic errors appropriately
- **File Persistence**: Saves and loads all data automatically to/from CSV-like files
- **Input Validation**: Robust console input with error handling

## Project Structure

```
Academic Enrollment Management System/
├── src/
│   ├── Main.java                          # Application entry point
│   ├── exception/
│   │   ├── CourseFullException.java       # Exception for full courses
│   │   ├── CourseNotFoundException.java    # Exception for missing courses
│   │   ├── StudentAlreadyEnrolledException.java  # Exception for duplicate enrollment
│   │   └── StudentNotFoundException.java   # Exception for missing students
│   ├── model/
│   │   ├── Person.java                    # Abstract base class
│   │   ├── Student.java                   # Student implementation with GPA tracking
│   │   ├── Instructor.java                # Instructor implementation
│   │   ├── Course.java                    # Course with capacity management
│   │   ├── Enrollment.java                # Enrollment record (auxiliary)
│   │   └── UniversityManager.java         # Central controller for all operations
│   └── service/
│       ├── ConsoleUI.java                 # Menu-driven user interface
│       └── FileManager.java               # File I/O for persistence
├── data/                                   # Persistent data storage
│   ├── students.txt                       # Student records
│   ├── courses.txt                        # Course records
│   ├── instructors.txt                    # Instructor records
│   └── enrollments.txt                    # Enrollment and grade records
├── bin/                                    # Compiled classes
├── build.gradle                           # Gradle build configuration
└── README.md                              # This file
```

## Installation & Setup

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- bash shell (for running commands)

### Compilation

```bash
cd "/home/clesence/IdeaProjects/Academic Enrollment Management System"
javac -d bin src/exception/*.java src/model/*.java src/service/*.java src/*.java
```

### Running the Application

```bash
cd "/home/clesence/IdeaProjects/Academic Enrollment Management System"
java -cp bin Main
```

## Usage Guide

### Main Menu Options

1. **Register Student** - Create a new student record
   - Input: Name, Email, Phone Number, Department
   - Output: Unique Student ID

2. **Create Course** - Add a new course to the system
   - Input: Course Name, Course Code, Credits, Student Capacity
   - Output: Course successfully created

3. **Enroll Student in Course** - Register a student for a course
   - Input: Student ID, Course Code
   - Validation: Checks for duplicate enrollment and course capacity
   - Output: Enrollment confirmation or error message

4. **View Student Record** - Display student information and enrolled courses
   - Input: Student ID
   - Output: Student info, GPA, enrolled courses with grades

5. **Assign Grade** - Add grades to student enrollments
   - Input: Student ID, Course Code, Grade (0-100)
   - Output: Grade confirmation and updated GPA

6. **Drop Course** - Remove a student from a course
   - Input: Student ID, Course Code
   - Output: Drop confirmation

7. **View Dean's List** - Display high-achieving students (GPA > 3.5)
   - Output: List of students meeting criteria

8. **View All Students** - Display all registered students with details
   - Output: Complete student roster with GPA and courses

9. **View All Courses** - Display all available courses
   - Output: Course list with enrollment status

10. **Register Instructor** - Create instructor record
    - Input: Name, Email, Phone Number, Department
    - Output: Unique Instructor ID

11. **Save and Exit** - Save all data and close application
    - Saves to files in the `data/` directory
    - Safely terminates the application

## Data Persistence

All data is automatically persisted to plain text files in the `data/` directory:

- **students.txt**: Student records (ID|Name|Email|Phone|Department|GPA)
- **courses.txt**: Course records (Code|Name|Credits|Capacity)
- **instructors.txt**: Instructor records (ID|Name|Email|Phone|Department)
- **enrollments.txt**: Enrollment records (StudentID|CourseCode|Grade)

Data is automatically loaded when the application starts and saved when the user exits.

## OOP Principles Applied

### Inheritance
- `Person` abstract class serves as base for Student and Instructor
- Promotes code reuse and polymorphism

### Encapsulation
- All fields are private with public getter/setter methods
- Controls access to internal state

### Polymorphism
- Abstract methods in Person are overridden in subclasses
- Different implementations of toString() and display methods

### Collections
- `List<Student>` for course rosters
- `List<Course>` for course management
- `Map<Course, Double>` for student-grade relationships
- `List<Enrollment>` for enrollment tracking

## Exception Handling

The system uses custom exceptions for business logic validation:

- **CourseFullException**: Thrown when attempting to enroll in a full course
- **StudentAlreadyEnrolledException**: Thrown when student already enrolled in course
- **StudentNotFoundException**: Thrown when student ID doesn't exist
- **CourseNotFoundException**: Thrown when course code doesn't exist

## File Format Examples

### students.txt
```
550e8400-e29b-41d4-a716-446655440000|John Doe|john@email.com|555-0001|Computer Science|3.75
550e8400-e29b-41d4-a716-446655440001|Jane Smith|jane@email.com|555-0002|Engineering|3.45
```

### courses.txt
```
CS101|Introduction to Computer Science|3|30
ENG201|Advanced English Literature|4|25
```

### enrollments.txt
```
550e8400-e29b-41d4-a716-446655440000|CS101|85.5
550e8400-e29b-41d4-a716-446655440001|ENG201|78.0
```

## Key Classes

### Person (Abstract)
Base class providing common attributes and methods for all people in the system.

**Fields**: name, email, phoneNumber
**Methods**: getName(), getEmail(), getPhoneNumber(), setters

### Student
Extends Person, manages student-specific information and course enrollments.

**Key Fields**: studentId, department, gpa, enrolledCourses (Map)
**Key Methods**: 
- enrollCourse(Course)
- addGrades(Course, double)
- calculateGPA()
- dropCourse(Course)
- displayStudentInfo()

### Instructor
Extends Person, manages instructor information and taught courses.

**Key Fields**: instructorId, department, coursesTaught (List)
**Key Methods**:
- assignCourse(Course)
- removeCourse(Course)

### Course
Manages course information and student roster.

**Key Fields**: courseName, courseCode, credits, studentCapacity, enrolledStudents
**Key Methods**:
- isFull()
- addStudent(Student)
- removeStudent(Student)
- getEnrolledCount()

### UniversityManager
Central controller coordinating all university operations.

**Key Methods**:
- registerStudent(Student)
- createCourse(Course)
- enrollStudentInCourse(Student, Course)
- assignGradeToStudent(Student, Course, grade)
- getDeansList()
- getAverageDepartmentGPA(String)
- getTopStudentInDepartment(String)

### FileManager
Handles all file I/O operations for persistence.

**Key Methods**:
- saveAll(UniversityManager)
- loadAll(UniversityManager)
- saveStudents(), loadStudents()
- saveCourses(), loadCourses()
- saveInstructors(), loadInstructors()
- saveEnrollments(), loadEnrollments()

### ConsoleUI
Provides the menu-driven user interface.

**Key Methods**:
- start() - Main application loop
- displayMainMenu() - Show menu options
- Various menu methods for each feature

## Sample Workflow

1. **Start the application**
   ```
   java -cp bin Main
   ```

2. **Create some courses** (Option 2)
   - Course: CS101 - Introduction to CS, 3 credits, 30 capacity
   - Course: MATH201 - Calculus II, 4 credits, 25 capacity

3. **Register students** (Option 1)
   - Student: John Doe, john@email.com, 555-0001, Computer Science
   - Student: Jane Smith, jane@email.com, 555-0002, Mathematics

4. **Enroll students** (Option 3)
   - Enroll John in CS101
   - Enroll Jane in MATH201

5. **Assign grades** (Option 5)
   - John CS101: 85.5
   - Jane MATH201: 92.0

6. **View results** (Options 4, 7, 8)
   - View individual student records
   - Check Dean's List (GPA > 3.5)
   - View all students and courses

7. **Exit and save** (Option 11)
   - Data is automatically saved to files

## Troubleshooting

### ClassNotFoundException: Main
- Ensure you're running the command from the correct directory
- Verify the bin/ directory contains compiled .class files
- Use: `java -cp bin Main`

### No data files created
- Check that the `data/` directory exists
- FileManager creates it automatically if missing
- Verify write permissions in the project directory

### Compilation errors
- Ensure all source files are in correct package directories
- Check for missing imports
- Verify Java version compatibility (JDK 8+)

## Learning Outcomes Achieved

✅ Applied OOP principles (inheritance, polymorphism, encapsulation)
✅ Mastered Java Collections (List, Map, Set for complex relationships)
✅ Implemented Custom Exception Handling for business logic
✅ Developed File Persistence without database dependency
✅ Created robust user interface with input validation
✅ Practiced Git/GitHub collaboration through version control

## Future Enhancements

- Database integration (MySQL/PostgreSQL)
- Web-based interface (Spring Boot)
- Email notifications for enrollment confirmations
- Advanced reporting and analytics
- Student transcript generation
- Prerequisite management for courses
- Waitlist management for full courses
- Grade distribution analysis
- Multi-semester/academic year support

## License

Educational project - Free to use and modify.

## Contact

For questions or issues, please refer to the project documentation or contact the development team.

---

**Version**: 1.0.0
**Last Updated**: February 24, 2026
**Java Version**: JDK 8+

