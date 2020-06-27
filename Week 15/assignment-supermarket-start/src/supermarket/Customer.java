package supermarket;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class Customer implements Callable<Integer> {

	public static final int MAX_ITEMS = 20;
	private final Store store;
	private final int customerNumber;
	private final int numberOfItemsWanted;
	private final static Random GENERATOR = new Random();
	
	public int getNumberOfItemsWanted() {
		return numberOfItemsWanted;
	}

	public Customer(int number, Store store) {
		this.store = store;
		customerNumber = number;
		numberOfItemsWanted = GENERATOR.nextInt(MAX_ITEMS) + 1;
	}

	@Override
	public Integer call() throws InterruptedException {
		int numberOfItemsBought = 0;

		List<Item> wantedItems = store.getItems(numberOfItemsWanted);    							// a) Collect all the items one wants to but from the store

		/** DEBUG */
		System.out.println("Customer " + customerNumber + " wants " + wantedItems.stream()
																					.map(i -> i.toString() + ", ")
																					.reduce("", String::concat)
		);

		wantedItems.add(null);																		// Simulate separating log
		Register register = store.claimRegister(GENERATOR.nextInt(Store.NUMBER_OF_CHECKOUTS));		// b) Choose a register.
		register.claim();																			// c) Claim the register.

		for (Item wantedItem : wantedItems) {														// d) Place all items on the belt.
			register.putOnBelt(wantedItem);
		}

		Item currentItem;																			// e) Grab all items from the bin, while counting them.
		do {
			currentItem = register.removeFromBin();

			if (currentItem!= null){
				numberOfItemsBought++;
			}
		} while (currentItem != null);

		register.free();

		return numberOfItemsBought;
	}
}
