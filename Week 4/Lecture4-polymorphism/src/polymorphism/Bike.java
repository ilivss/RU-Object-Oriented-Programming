package polymorphism;

/**
 *
 * @author Sjaak Smetsers
 */
public class Bike extends Vehicle {

    private int myNrofGears;

    public Bike() {
        myNrofGears = 1;
    }

    public Bike(int g) {
        myNrofGears = g;
    }

    @Override
    public String toString() {
        return String.format("A bike with %d gears", myNrofGears);
    }
}
