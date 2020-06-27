/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drinkersandbar;

import java.util.Random;

/**
 *
 * @author Sjaak
 */
public class Util {
    private static Random generator = new Random(); 
    
    public static int randomInRange(int from, int to) {
        if (from == to) {
            return from;
        } else {
            return from + generator.nextInt(to - from);
        }
    }

    public static void takeABreak( int min, int max ) {
        try {
            int time = randomInRange(min, max);
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println("Unexpected interrupt");
            System.exit(0);
        }
    }

    public static void takeABreak( int time ) {
        takeABreak( 0, time );
    }
    
    public static void takeAnExactBreak( int time ) {
        takeABreak( time, time );
    }
}
