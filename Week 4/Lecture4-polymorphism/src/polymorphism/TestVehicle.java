package polymorphism;

/**
 *
 * @author Sjaak Smetsers
 */
public class TestVehicle {

    public static void main(String[] args) {
        testDealer();
        
    }
    
    private static void testVehicle() {
        Car car         = new Car("LS-HL-97");
        MotorVehicle mv = new MotorVehicle("RV-ZJ-42");
        Bike bike       = new Bike(7);
        Bus bus         = new Bus("AB-CD-01", 40);

        car.show();
        mv.show();
        bike.show();
        bus.show();
    }

    private static void testDealer() {
        Car car         = new Car("LS-HL-97");
        Bike bike       = new Bike(7);
        Bus bus         = new Bus("AB-CD-01", 40);

        Dealer dealer = new Dealer();

        dealer.buy(car, 5600);
        dealer.buy(bus, 12800);
        dealer.buy(bike, 129.95);

        dealer.showStock();
    }
    
}

