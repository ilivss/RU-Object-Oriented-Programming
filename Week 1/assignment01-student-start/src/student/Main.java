package student;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);                                    // Scanner to parse the input line.
        
        // Creating group.
        System.out.println("\nPlease enter the groupsize:");
        int i = in.nextInt();
        Group group = new Group(i);                                             // Creating the group with size i.
        
        // Adding students to the group.
        for (Student s : group.getStudents()) {                                 // Adding i number of students to the object "group". Using a Enhanced for loop. 
            
            System.out.println("\nPlease  enter a student in the following format: Firstname Lastname Studentnumber. E.g: John Doe 1");
           
            String fname = in.next();
            String lname = in.next();
            int sNumber = in.nextInt();

            group.addStudent(fname, lname, sNumber);
        }
        
        // Showing all students from the group.
        System.out.println("\nThe group now contains:");
        System.out.println(group.toString());
        
        // Changing the students untill negative number is filled in.
        int n = 1;
        System.out.println("\nStudent number and new given/family 1name?");
        
        
        while (n > 0)
        {
            int sNumber = in.nextInt();
            n = sNumber;
            String fname = in.next();
            String lname = in.next();
            
            group.changeStudent(sNumber, fname, lname);            
            
            // Showing all students from the group.
            System.out.println("\nThe group now contains:");
            System.out.println(group.toString());          
        }
        
        System.out.println("Bye!");
        in.close();                                                             // close scanner 
    }
}