package polymorphism;

/**
 *
 * @author Sjaak Smetsers
 */
public class MotorVehicle extends Vehicle {

    private String myLicenseNr;

    public MotorVehicle(String ln) {
        myLicenseNr = ln;
    }

    public String getLicenseNr() {
        return myLicenseNr;
    }

    @Override
    public String toString() {
        return "A motor vehicle with reg. no. " + myLicenseNr;
    }
}
