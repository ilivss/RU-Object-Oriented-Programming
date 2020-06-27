package student;

public class Assignment01Tester {
        private Group g;
        
	public void createGroup(int i) {
            g = new Group(i);
	}

	public void addStudent(int sNumber, String firstName, String lastName) {
            g.addStudent(firstName, lastName, sNumber);
	}

	public void changeStudent(int sNumber, String firstName, String lastName) {
            g.changeStudent(sNumber, firstName, lastName);
	}

	public String printStudents() {
            return g.toString();
	}

}