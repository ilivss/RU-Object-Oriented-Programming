package polymorphism;

/**
 *
 * @author Sjaak Smetsers
 */
public class Bus extends MotorVehicle {

    private int myNrOfSeats;

    public Bus(String ln, int s) {
        super(ln);
        myNrOfSeats = s;
    }

    @Override
    public String toString() {
        return String.format("A bus with reg. no. %s and %d seats", getLicenseNr(), myNrOfSeats);
    }

}
