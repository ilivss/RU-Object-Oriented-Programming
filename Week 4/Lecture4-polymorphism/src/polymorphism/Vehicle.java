package polymorphism;

/**
 *
 * @author Sjaak Smetsers
 */
public class Vehicle {

    @Override
    public String toString() {
        return "A vehicle";
    }

    public void show() {
        System.out.println(toString());
    }
};
