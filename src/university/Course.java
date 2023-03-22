package university;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Course {

	private final String title;
	private String teacher;
	private final Integer id;
	private ArrayList<Integer> attendees = new ArrayList<>();
	private HashMap<Integer, Integer> grades = new HashMap<>();

	private static int idCounter = 10;

	public Course(String title, String teacher) {
		this.title = title;
		this.teacher = teacher;
		this.id = idCounter++;
	}

	public String getTitle() {
		return title;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public Integer getId() {
		return id;
	}

	public void register(Integer studentID) {
		if (attendees.size() >= 100) {
			throw new RuntimeException("Course is already full");
		}
		if (attendees.contains(studentID)) {
			throw new RuntimeException("Student is already registered for this course");
		}
		attendees.add(studentID);
	}

	public ArrayList<Integer> attendees() {
		return this.attendees;
	}

	public HashMap<Integer, Integer> grades() {
		return grades;
	}

	@Override
	public String toString() {
		return id + "," + title + "," + teacher;
	}

	public void exam(Integer studentID, Integer grade) {
		if (!attendees.contains(studentID)) {
			throw new RuntimeException("Student is not registered for this course");
		}
		if (grades.containsKey(studentID)) {
			throw new RuntimeException("Student has already taken the exam for this course");
		}
		if (grade < 0 || grade > 30) {
			throw new RuntimeException("Grade must be between 0 and 30");
		}
		grades.put(studentID, grade);
	}

	public String courseAvg() {

		double sum = 0;
		double count = 0;

		if (grades.isEmpty()) {
			return "No student has taken the exam in " + title;
		}
		for (Map.Entry<Integer, Integer> entry : grades.entrySet()) {
			sum += entry.getValue();
			count++;
		}
		double avg = sum / count;
		return String.valueOf(avg);
	}

}
