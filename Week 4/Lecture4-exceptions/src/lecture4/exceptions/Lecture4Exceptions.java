package lecture4.exceptions;

import java.util.Scanner;

/**
 *
 * @author Sjaak Smetsers <s.smetsers@cs.ru.nl>
 */
public class Lecture4Exceptions {

    public static void main(String[] args) {
        arithInfinity2();
    }

    private static void inputException() {
        Scanner scan = new Scanner("The answer is 42");
        int i = scan.nextInt();
        System.out.println("\"" + i + "\"");
    }

    private static void nullException() {
        Object obj = null;
        System.out.println( obj.toString() );
    }

    private static void arithInfinity() {
        double d = 4711.0/0;
        System.out.println( d );
    }

    private static void arithInfinity2() {
        double d = -4711.0/0;
        System.out.println( d );
    }

    private static void arithInfinity3() {
        double d1 = -4711.0/0;
        double d2 = 4711.0/0;
        System.out.println( d1 + d2 );
    }

    private static void arithException() {
        int i = 4711/0;
        System.out.println( i );
    }
    private static void ooBException() {
        int[] a = { 1, 2, 3 };
        System.out.println( a[3] );
    }
}
