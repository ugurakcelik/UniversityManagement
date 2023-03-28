package university;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Logger;

public class University {
	
	private final int MAX_STUDENTS = 1000;
	private final int MAX_COURSES = 1000;
	private final int MAX_ATTENDEES = 100;
	private final int MAX_COURSES_PER_STUDENT = 25;

	private static int courseId;
	private static int studentId;
	
	private String name;
	private Rector rector = new Rector();
	private ArrayList<Student> student = new ArrayList<>();
	private ArrayList<Course> course = new ArrayList<>();
	
	
	public University(String name){

		courseId = 10;
		studentId = 10000;

		logger.info("Creating university " + name);
		this.name = name;
	}
	
	/**
	 * Getter for the name of the university
	 * 
	 * @return name of university
	 */
	public String getName(){	
		return name;
	}
	
	/**
	 * Defines the rector for the university
	 * 
	 * @param first first name of the rector
	 * @param last	last name of the rector
	 */
	public void setRector(String first, String last){
		rector.setFirst(first);
		rector.setLast(last);
	}
	
	/**
	 * Retrieves the rector of the university
	 * 
	 * @return name of the rector
	 */
	public String getRector(){
		return rector.toString();
	}
	
	/**
	 * Enrol a student in the university
	 * 
	 * @param first first name of the student
	 * @param last last name of the student
	 * 
	 * @return unique ID of the newly enrolled student
	 */
	public int enroll(String first, String last){
		if(student.size() >= MAX_STUDENTS ) {
			throw new RuntimeException("Maximum number of students reached.");
		}
		Student tmp = new Student(first, last, studentId++);
		logger.info("New student enrolled: " + tmp.getId() + ", " + tmp.getFirst() + " " + tmp.getLast());
		student.add(tmp);
		return tmp.getId();
	}
	
	/**
	 * Retrieves the information for a given student
	 * 
	 * @param id the ID of the student
	 * 
	 * @return information about the student
	 */
	public String student(int id){
		Student tmp = student.stream().filter(students -> students.getId().equals(id)).findFirst().orElse(null);
		if(tmp == null) {
			throw new RuntimeException("Student not found.");
		}
		return tmp.toString();
	}
	
	/**
	 * Activates a new course with the given teacher
	 * 
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * 
	 * @return the unique code assigned to the course
	 */
	public int activate(String title, String teacher){	
		if(course.size() >= MAX_COURSES) {
			throw new RuntimeException("Maximum number of courses reached.");
		}
		Course c = new Course(title, teacher, courseId++);
		logger.info("New course activated: " + c.getId() + ", " + c.getTitle() + " " + c.getTeacher());
		course.add(c);
		return c.getId();
	}
	
	/**
	 * Retrieve the information for a given course.
	 * 
	 * The course information is formatted as a string containing 
	 * code, title, and teacher separated by commas, 
	 * e.g., {@code "10,Object Oriented Programming,James Gosling"}.
	 * 
	 * @param code unique code of the course
	 * 
	 * @return information about the course
	 */
	public String course(int code){
		Course tmp = course.stream().filter(courses -> courses.getId().equals(code)).findFirst().orElse(null);

		if(tmp == null) {
			throw new RuntimeException("Course not found.");
		}
		return tmp.toString();
	}
	
// R4
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
		Course cTmp = course.stream().filter(courses -> courses.getId().equals(courseCode)).findFirst().orElse(null);
		Student stTmp = student.stream().filter(students -> students.getId().equals(studentID)).findFirst().orElse(null);

		if(cTmp == null || stTmp == null ) {
			throw new RuntimeException("No course or student matched.");
		}
		if(cTmp.attendees().size() >= MAX_ATTENDEES) {
			throw new RuntimeException("Maximum number of attendees reached for " + cTmp.getTitle());
		}
		if (course.stream().filter(courses -> courses.attendees().contains(studentID)).count() >= MAX_COURSES_PER_STUDENT) {
			throw new RuntimeException("Student cannot attend no more than " +MAX_COURSES_PER_STUDENT + " distinct courses.");
		}

		logger.info("Student " + stTmp.getId() + " signed up for course " + cTmp.getId());
		cTmp.register(stTmp.getId());
	}
	
	/**
	 * Retrieve a list of attendees
	 * 
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */
	public String listAttendees(int courseCode){
		StringBuilder str = new StringBuilder();
		Course c = course.stream().filter(courses -> courses.getId().equals(courseCode)).findFirst().orElse(null);
		for(int i=0; i < c.attendees().size(); i++) {
			int studentID = c.attendees().get(i);
			str.append(student.stream().filter(students -> students.getId().equals(studentID)).findFirst().orElse(null).toString()+ "\n");
		}

		return (str.length() == 0) ? "No attendees" : str.toString().trim();
		
	}

	/**
	 * Retrieves the study plan for a student.
	 * 
	 * The study plan is reported as a string having
	 * one course per line (i.e. separated by '\n').
	 * The courses are formatted as describe in method {@link #course}
	 * 
	 * @param studentID id of the student
	 * 
	 * @return the list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		//TODO: to be implemented
		StringBuilder str = new StringBuilder();
		for(int i=0; i < course.size(); i++) {
			
			if(course.get(i).attendees().contains(studentID)) {
				str.append(course.get(i).toString() + "\n");
			}
		}
		return (str.length() == 0) ? "No courses" : str.toString().trim();
	}

	/**
	 * records the grade (integer 0-30) for an exam can 
	 * 
	 * @param studentId the ID of the student
	 * @param courseID	course code 
	 * @param grade		grade ( 0-30)
	 */
	public void exam(int studentId, int courseID, int grade) {
		
	    Course cTmp = course.stream().filter(courses -> courses.getId().equals(courseID)).findFirst().orElse(null);
		Student stTmp = student.stream().filter(students -> students.getId().equals(studentId)).findFirst().orElse(null);
	    logger.info("Student " + studentId + " took an exam in course " + courseID + " with grade " + grade);
	    cTmp.exam(stTmp.getId(), grade);
	}

	/**
	 * Computes the average grade for a student and formats it as a string
	 * using the following format 
	 * 
	 * {@code "Student STUDENT_ID : AVG_GRADE"}. 
	 * 
	 * If the student has no exam recorded the method
	 * returns {@code "Student STUDENT_ID hasn't taken any exams"}.
	 * 
	 * @param studentId the ID of the student
	 * @return the average grade formatted as a string.
	 */
	public String studentAvg(int studentId) {
		double sum = 0;
		double count = 0;
		for(Course courses : course) {
			if(courses.attendees().contains(studentId) && courses.grades().containsKey(studentId)) {
				sum +=courses.grades().get(studentId);
				count++;
			}
		}		
		if (count == 0) {
	        return "Student " + studentId + " hasn't taken any exams";
	    } else {
	        double avg = (double) sum / count;
	        return String.format("Student %d : %.1f", studentId, avg);
	    }
	}
	
	public double studentAvgDouble(int studentId) {
		double sum = 0;
		double count = 0;
		double enrolled = 0;
		for(Course courses : course) {
			if(courses.attendees().contains(studentId) && courses.grades().containsKey(studentId)) {
				sum +=courses.grades().get(studentId);
				count++;
			}
		}		
		if (count == 0) {
	        return 0;
	    } else {
	        double avg = sum / count;
	        return avg;
	    }
	}
	
	/**
	 * Computes the average grades of all students that took the exam for a given course.
	 * 
	 * The format is the following: 
	 * {@code "The average for the course COURSE_TITLE is: COURSE_AVG"}.
	 * 
	 * If no student took the exam for that course it returns {@code "No student has taken the exam in COURSE_TITLE"}.
	 * 
	 * @param courseId	course code 
	 * @return the course average formatted as a string
	 */
	public String courseAvg(int courseId) {
		
		Course c = course.stream().filter(courses -> courses.getId().equals(courseId)).findFirst().orElse(null);
		
		return (c.getTitle() + " average : " + c.courseAvg());
	}
	
	/**
	 * Retrieve information for the best students to award a price.
	 * 
	 * The students' score is evaluated as the average grade of the exams they've taken. 
	 * 
	 * The method returns a string with the information about the three students with the highest score. 
	 * The students appear one per row (rows are terminated by a new-line character {@code '\n'}) 
	 * and each one of them is formatted as: {@code "STUDENT_FIRSTNAME STUDENT_LASTNAME : SCORE"}.
	 * 
	 * @return info on the best three students.
	 */
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
	          str.append(student.get(i).getFirst())
	            .append(" ")
	            .append(student.get(i).getLast())
	            .append(" : ")
	            .append(studentAvgDouble(student.get(i).getId()))
	            .append("\n");
	      }
	      return str.toString().trim();
	  }

    /**
     * This field points to the logger for the class that can be used
     * throughout the methods to log the activities.
     */
    private final static Logger logger = Logger.getLogger("University");

}
