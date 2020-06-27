package supermarket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Register {

	// Make sure that CONVEYOR_SIZE + BIN_SIZE >= Customer.MAX_ITEMS, otherwise
	// danger of deadlock
	private static final int CONVEYER_SIZE = 10, BIN_SIZE = 10;
	private final ConveyorBelt<Item> belt;
	private final ConveyorBelt<Item> bin;

	private final Lock lock;

	public Register() {
		belt = new ConveyorBelt<>(CONVEYER_SIZE);
		bin = new ConveyorBelt<>(BIN_SIZE);
		lock = new ReentrantLock();
	}

	public void putOnBelt(Item article) throws InterruptedException {
		belt.putIn(article);
	}

	public Item removeFromBelt() throws InterruptedException {
		return belt.removeFrom();
	}

	public void putInBin(Item article) throws InterruptedException {
		bin.putIn(article);
	}

	public Item removeFromBin() throws InterruptedException {
		return bin.removeFrom();
	}

	public void claim() {
		lock.lock();
	}

	public void free() {
		lock.unlock();
	}
}
