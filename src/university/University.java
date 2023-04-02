package university;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Logger;

import javax.management.RuntimeErrorException;

/**
 * This class represents a university education system.
 * 
 * It manages students and courses.
 *
 */
public class University {

	private final int MAX_STUDENTS = 1000;

	private final int MAX_COURSES = 1000;

	private final int MAX_ATTENDEES = 100;

	private final int MAX_COURSES_PER_STUDENT = 25;

	private String name;
	private String rector;

	private static int courseId;
	private static int studentId;

	private ArrayList<Student> student = new ArrayList<>();
	private ArrayList<Course> course = new ArrayList<>();

	public University(String name) {

		courseId = 10;
		studentId = 10000;

		logger.info("Creating university " + name);
		this.name = name;
	}
	
	public void setRector(String name) {
		rector = name;
	}
	
	public String getRector() {
		return rector;
	}
	
	public String getName() {
		return name;
	}

	public int enroll(String first, String last) {
		if (student.size() >= MAX_STUDENTS) {
			throw new RuntimeException("Maximum number of students reached.");
		}
		Student tmp = new Student(first, last, studentId++);
		logger.info("New student enrolled: " + tmp.getId() + ", " + tmp.getFirst() + " " + tmp.getLast());
		student.add(tmp);
		return tmp.getId();
	}

	public String student(int id) {

		Student tmp = findStudentById(id);
		if (tmp == null) {
			throw new RuntimeException("Student not found.");
		}
		return tmp.toString();
	}

	public int activate(String title, String teacher) {

		if (course.size() >= MAX_COURSES) {
			throw new RuntimeException("Maximum number of courses reached.");
		}
		Course c = new Course(title, teacher, courseId++);
		logger.info("New course activated: " + c.getId() + ", " + c.getTitle() + " " + c.getTeacher());
		course.add(c);
		return c.getId().intValue();
	}

	public String course(int code) {

		Course tmp = findCourseById(code);
		if (tmp == null) {
			throw new RuntimeException("Course not found.");
		}
		return tmp.toString();
	}

	public void register(int studentID, int courseCode) {

		Course cTmp = findCourseById(courseCode);
		Student sTmp = findStudentById(studentID);

		if (cTmp == null || sTmp == null) {
			throw new RuntimeException("No course or student matched.");
		}
		if (cTmp.attendees().size() >= MAX_ATTENDEES) {
			throw new RuntimeException("Maximum number of attendees reached for " + cTmp.getTitle());
		}
		if (course.stream().filter(courses -> courses.attendees().contains(studentID))
				.count() >= MAX_COURSES_PER_STUDENT) {
			throw new RuntimeException(
					"Student cannot attend no more than " + MAX_COURSES_PER_STUDENT + " distinct courses.");
		}

		logger.info("Student " + sTmp.getId() + " signed up for course " + cTmp.getId());
		cTmp.register(sTmp.getId());
	}

	public String listAttendees(int courseCode) {

		StringBuilder str = new StringBuilder();
		Course c = findCourseById(courseCode);

		for (int i = 0; i < c.attendees().size(); i++) {
			int studentID = c.attendees().get(i);
			str.append(findStudentById(studentID).toString()).append("\n");
		}
		return (str.length() == 0) ? "No attendees" : str.toString().trim();
	}

	public String studyPlan(int studentID) {

		StringBuilder str = new StringBuilder();
		for (Course value : course) {

			if (value.attendees().contains(studentID)) {
				str.append(value).append("\n");
			}
		}
		return (str.length() == 0) ? "No courses" : str.toString().trim();
	}

	public void exam(int studentId, int courseID, int grade) {

		Course cTmp = findCourseById(courseID);
		Student sTmp = findStudentById(studentId);

		if (cTmp == null || sTmp == null) {
			throw new RuntimeException("No course or student matched.");
		}

		logger.info("Student " + studentId + " took an exam in course " + courseID + " with grade " + grade);
		cTmp.exam(sTmp.getId(), grade);
	}

	public String studentAvg(int studentId) {
		double avg = studentAvgDouble(studentId);
		if (avg == 0) {
			return "Student " + studentId + " hasn't taken any exams";
		} else {
			return String.format("Student %d : %.1f", studentId, avg);
		}
	}

	public double studentAvgDouble(int studentId) {
		double sum = 0;
		double count = 0;
		for (Course courses : course) {
			if (courses.attendees().contains(studentId)) {
				Integer grade = courses.getGradeByStudentId(studentId);
				if (!grade.equals(0)) {
					sum += grade;
					count++;
				}
			}
		}
		if (count == 0) {
			return 0;
		} else {
			return sum / count;
		}
	}

	public String courseAvg(int courseId) {

		Course c = findCourseById(courseId);

		return (c.getTitle() + " average : " + c.courseAvg());
	}

	public String topThreeStudents() {

		Collections.sort(student, new Comparator<Student>() {
			public int compare(Student s1, Student s2) {
				double s1Score = studentAvgDouble(s1.getId());
				double s2Score = studentAvgDouble(s2.getId());
				return Double.compare(s2Score, s1Score);
			}
		});

		int sample = (int) student.stream().filter(s -> studentAvgDouble(s.getId()) > 0).limit(3).count();

		StringBuilder str = new StringBuilder();
		for (int i = 0; i < sample; i++) {
			str.append(student.get(i).getFirst()).append(" ").append(student.get(i).getLast()).append(" : ")
					.append(studentAvgDouble(student.get(i).getId())).append("\n");
		}
		return str.toString().trim();
	}

	public Student findStudentById(int id) {
		return student.stream().filter(students -> students.getId().equals(id)).findFirst().orElse(null);
	}

	public Course findCourseById(int id) {
		return course.stream().filter(courses -> courses.getId().equals(id)).findFirst().orElse(null);
	}

	private final static Logger logger = Logger.getLogger("University");

}