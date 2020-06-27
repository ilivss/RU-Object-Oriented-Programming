package drinkersandbar;

/**
 *
 * @author Sjaak Smetsers <s.smetsers@cs.ru.nl>
 */
public class Main {
    
    public static void main(String[] args) {
        Simulation sim = new Simulation();
        sim.simulateSequentially();
        sim.simulateConcurrently();
    }

}
