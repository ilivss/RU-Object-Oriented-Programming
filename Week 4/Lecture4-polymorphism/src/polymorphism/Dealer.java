package polymorphism;

import java.util.ArrayList;

/**
 *
 * @author Sjaak Smetsers
 */
public class Dealer {

    static private class Item {

        private Vehicle vehicle;
        private double price;

        private Item(Vehicle v, double p) {
            vehicle = v;
            price = p;
        }

        private void show() {
            System.out.printf("%s, price $%.2f\n", vehicle.toString(), price);
        }

    }

    ArrayList<Item> stock;

    public Dealer() {
        stock = new ArrayList<Item>();
    }

    public void buy(Vehicle v, double p) {
        stock.add(new Item(v, p));
    }

    public void showStock() {
        for (Item it : stock) {
            it.show();
        }
    }
}
