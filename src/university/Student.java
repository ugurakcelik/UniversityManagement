package university;

public class Student {
	
	private String first;
	private String last;
	private final Integer id;
	
	public Student(String first, String last, int id) {
		this.first = first;
		this.last = last;
		this.id = id;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public Integer getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return id + " " + first + " " + last;
	}
}
