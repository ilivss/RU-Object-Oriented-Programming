
package hidden;

import drinkersandbar.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;


public class Simulation {
    public static final int NR_OF_GLASSES = 3;
    public static final int NR_OF_DRINKERS = 5;

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
        Bar bar = new Bar(NR_OF_GLASSES);
        ExecutorService executor = Executors.newCachedThreadPool();
        IntStream.rangeClosed( 1, NR_OF_DRINKERS ).
                mapToObj( id -> new Drinker( id, bar ) ).
                forEach( executor::execute );
        executor.shutdown();
    }
}
