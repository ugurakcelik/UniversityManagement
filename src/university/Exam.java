package university;

public class Exam {

	    private Integer studentId;

	    private Integer grade;

	    public Exam (Integer studentId, int grade){
	        this.studentId = studentId;
	        this.grade = grade;
	    }

		public Integer getStudentId() {
			return studentId;
		}

		public void setStudentId(Integer studentId) {
			this.studentId = studentId;
		}

		public int getGrade() {
			return grade;
		}

		public void setGrade(int grade) {
			this.grade = grade;
		}	    
	    
}
