/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

/**
 *
 * @author s4707079 & s1037202
 */
public class Group {
    private Student[] students;                                                 // array with all students in group.
    private int size = 0;
    private int current = 0;
    
    public Group (int i) {
        this.size = i;
        this.students = new Student[size];
    }
    
    public Student[] getStudents() {
        return this.students;
    }
    
    public void addStudent (String firstname, String lastname, int snumber) {
        this.students[current++] = new Student (firstname, lastname, snumber);
    }
    
    public void changeStudent (int sNumber, String fname, String lname) {
        // search for the sNumber in the group:
        for (Student s : this.students) {
            if (s.getSnumber() == sNumber) {
                s.changeName(fname, lname);
            }
        }
    }
    
    @Override
    public String toString() {
        String output = "";                                                     // Could have accomplished this also with StringBuilder. But this was faster for me.
        for (Student s : this.students)
        {
            output += s.toString() + "\n";
        }
        return output;
    }
}
