package geometric;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author s4707079 & s1037202
 */

public class View {

    private Scanner scan;

    public View() {
        this.scan = new Scanner(System.in);
    }

    public String getCommand (){
        System.out.println("Enter a command: ");
        return scan.next().toLowerCase();
    }

    public void wrongCommand (){
        System.out.println("Please enter a valid command.");
    }

    public double getDouble (){
        double test = 777;

        if (scan.hasNextDouble()) {
            try {
                test = scan.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("No valid double, please try again.");
            }
        }
        return test;
    }

    public int getInt(){
        return scan.nextInt();
    }

    public char getChar(){
        return scan.next().toLowerCase().charAt(0);
    }

    public void print (String input) {
        System.out.println(input);
    }

    public String getNextLine (){
        return scan.nextLine();
    }
}
