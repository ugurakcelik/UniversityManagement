# University Management Project

This project is an implementation of a university management system that uses an Object-Oriented Programming (OOP) structure to model the entities in a university, such as students, courses, and the university itself.

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

### Constructors

* `University(String name)`: creates a new `University` object with the given name.

### Methods

* `getName()`: gets the name of the university.
* `setRector(String first, String last)`: sets the name of the rector of the university.
* `getRector()`: gets the name of the rector of the university.
* `enroll(String first, String last)`: enrolls a new student in the university.
* `student(int id)`: retrieves the information for a given student.
* `activate(String title, String teacher)`: activates a new course with the given title and teacher.
* `course(int code)`: retrieves the information for a given course.
* `register(int studentID, int courseCode)`: registers a student to attend a course.
* `listAttendees(int courseCode)`: retrieves a list of attendees for a course.
* `studyPlan(int studentID)`: retrieves the study plan for a given student.
