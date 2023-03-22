# UniversityManagement

This Java program simulates a university using an Object Oriented structure. It creates a university object, sets the rector, enrolls students, activates courses, registers students for courses, lists attendees, prints study plans, takes exams, and calculates student and course averages. The `University` class has the following functionalities:

Enroll a student in the university: Using the enroll() method, a user can enroll a student in the university by providing the student's first and last name. The method returns a unique ID for the newly enrolled student.

Retrieve information about a student: Using the student() method, a user can retrieve information about a student by providing the student's ID. The method returns a string containing the student's information, such as their first and last name.

Activate a new course: Using the activate() method, a user can activate a new course with a given title and teacher name. The method returns a unique code for the newly activated course.

Retrieve information about a course: Using the course() method, a user can retrieve information about a course by providing its unique code. The method returns a string containing the course's information, such as its title and teacher name.

Register a student for a course: Using the register() method, a user can register a student to attend a course by providing the student's ID and the course code. The method checks if the course has reached its maximum number of attendees and if the student is not already registered for too many courses. If the registration is successful, the method logs the registration.

Retrieve a list of attendees for a course: Using the listAttendees() method, a user can retrieve a list of attendees for a course by providing its unique code. The method returns a string containing the information of each attendee separated by a new line.

Retrieve the study plan for a student: Using the studyPlan() method, a user can retrieve the study plan for a student by providing their ID. The method returns a string containing the information for each course the student is registered to attend, separated by a new line.

The University class also includes constants for the maximum number of students, courses, attendees per course, and courses per student. The class uses an ArrayList to store the students and courses, and a Logger object to log events such as new student enrollments, new course activations, and student course registrations.
