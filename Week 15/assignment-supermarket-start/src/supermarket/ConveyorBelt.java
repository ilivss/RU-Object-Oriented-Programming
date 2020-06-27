package supermarket;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConveyorBelt <T> {

	private final T[] elements;
	private int amount, begin, end;

	private Lock lock = new ReentrantLock();
	private Condition isNotEmpty = lock.newCondition();
	private Condition isNotFull = lock.newCondition();

	public ConveyorBelt(int size) {
		elements = (T[]) new Object[size];
		amount = 0;
		begin = 0;
		end = 0;
	}

	public void putIn(T item) throws InterruptedException {
		lock.lock();
		try {
			while (amount >= elements.length){
				isNotFull.await();
			}

			elements[end] = item;
			end = (end + 1) % elements.length;
			amount = amount + 1;
		} finally {
			isNotEmpty.signalAll();
			lock.unlock();
		}


	}

	public T removeFrom() throws InterruptedException { // Assumes there is at least one element
		lock.lock();
		try {
			while (amount <= 0) {
				isNotEmpty.await();
			}

			T item = elements[begin];
			begin = (begin + 1) % elements.length;
			amount = amount - 1;
			return item;
		} finally {
			isNotFull.signalAll();
			lock.unlock();
		}
	}
}
