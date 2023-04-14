# University Management Project

This project is an implementation of a university management system that uses an **Object-Oriented Programming (OOP)** structure to model the entities in a university, such as students, courses, and the university itself. The project uses **Spring Boot** and **Spring Data JPA** to interact with a relational database.The **Lombok** library is used to generate getters, setters, and toString methods for the University class.

The main class contains the entry point for the program, which initializes an instance of the University class, sets the name of the university, and creates and enrolls students into it. The program also activates courses and registers students in them, schedules exams, and calculates student and course averages.

## Overview

The `University` class is used to represent a university, with properties and methods to manage students and courses. The class provides the following features:

* Enrolling students
* Registering students for courses
* Activating new courses
* Retrieving information about students and courses
* Listing attendees for a course
* Retrieving a student's study plan

## Class Members

### Fields

* `MAX_STUDENTS`: an integer constant representing the maximum number of students allowed in the university.
* `MAX_COURSES`: an integer constant representing the maximum number of courses allowed in the university.
* `MAX_ATTENDEES`: an integer constant representing the maximum number of attendees allowed for a course.
* `MAX_COURSES_PER_STUDENT`: an integer constant representing the maximum number of courses a student can attend.
* `name`: a string representing the name of the university.
* `rector`: an instance of the `Rector` class representing the rector of the university.
* `student`: an `ArrayList` containing instances of the `Student` class representing the enrolled students.
* `course`: an `ArrayList` containing instances of the `Course` class representing the activated courses.

## Constructors

#### `public University(String name)`: creates a new `University` object with the given name.

## Methods

#### `public String getName()`

Returns the name of the university.

#### `public void setRector(String first, String last)`

Sets the first and last name of the rector.

#### `public String getRector()`

Returns a string representation of the rector.

#### `public int enroll(String first, String last)`

Enrolls a new student with the given first and last name. Returns the ID of the new student.

#### `public String student(int id)`

Returns a string representation of the student with the given ID.

#### `public int activate(String title, String teacher)`

Activates a new course with the given title and teacher. Returns the code of the new course.

#### `public String course(int code)`

Returns a string representation of the course with the given code.

#### `public void register(int studentID, int courseCode)`

Registers the student with the given ID for the course with the given code.

#### `public String listAttendees(int courseCode)`

Returns a string representation of the attendees for the course with the given code.

#### `public String studyPlan(int studentID)`

Returns a string representation of the courses the student with the given ID is enrolled in.

#### `public void exam(int studentId, int courseID, int grade)`

Records the grade for the student with the given ID for the exam in the course with the given code.

#### `public String studentAvg(int studentId)`

Returns the average grade for the student with the given ID.

#### `public double studentAvgDouble(int studentId)`

Returns the average grade for the student with the given ID as a double.

#### `public String courseAvg(int courseId)`

Returns the average grade for the course with the given code.

#### `public String topThreeStudents()`

Returns a string representation of the top three students with the highest average score.


