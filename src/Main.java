import university.*;

public class Main {

	public static void main(String[] args) {
		
				String universityName = "Politecnico di Torino";
				
				University poli = new University(universityName);
				
				poli.setRector("Guido Saracco");
				
				System.out.println("Rector of " + poli.getName() + " : " + poli.getRector());
				
		
				int s1 = poli.enroll("Saul","Goodman");
				int s2 = poli.enroll("Hector","Salamanca");
				
				System.out.println("Enrolled students " + s1 + ", " + s2); // 10000, 10001
				System.out.println("s1 = " + poli.student(s1)); // 10000 Saul Goodman


				int macro = poli.activate("Macro Economics", "Paul Krugman");
				int oop = poli.activate("Object Oriented Programming", "James Gosling");
				
				System.out.println("Activated courses " + macro + " and " + oop); // 10 and 11

		
				poli.register(s1, macro);
				poli.register(s2, macro);
				poli.register(s2, oop);
				
				System.out.println(poli.listAttendees(macro));
				// 10000 Saul Goodman
				// 10001 Hector Salamanca
				
				System.out.println(poli.studyPlan(s2));
				// 10,Macro Economics,Paul Krugman
				// 11,Object Oriented Programming,James Gosling


				poli.exam(s1, macro, 27);
				poli.exam(s2, macro, 30);
				poli.exam(s2, oop, 28);
				
				System.out.println(poli.studentAvg(s2)); // 29.0
				
				System.out.println(poli.courseAvg(macro)); // 28.5
		        
				System.out.println("Best students:\n" + poli.topThreeStudents());
			}
		}
