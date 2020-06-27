
package drinkersandbar;

import java.util.LinkedList;
import java.util.Queue;

public class Simulation {
    public static final int NR_OF_GLASSES = 2;
    public static final int NR_OF_DRINKERS = 4;

    public void simulateSequentially() {
        Bar bar = new Bar(NR_OF_GLASSES);
        Queue<Drinker> drinkers = new LinkedList<>();
        for (int i = 0; i < NR_OF_DRINKERS; i++) { // fill the queue
            drinkers.offer(new Drinker(i, bar));
        }
        while (!drinkers.isEmpty()) {
            Drinker drinker = drinkers.poll(); // get the first waiting drinker
            drinker.drawAndDrink();      // make the drinker take a drink
            if (!drinker.isSatisfied()) {
                drinkers.offer(drinker); // put drinker back at end of queue
            }
        }
    }

    public void simulateConcurrently() {
        // Implement this
    }
}
