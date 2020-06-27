package taxi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class that holds the number of persons arriving by train at the station and
 * waiting for a taxi
 */
public class Station {

	private int nrOfPassengersAtStation = 0;
	private int totalNrOfPassengers = 0;
	private boolean isClosed = false;

	private final Lock lock = new ReentrantLock();
	private final Condition busyStation = lock.newCondition();
	private final Condition emptyStation = lock.newCondition();


	public void enterStation(int nrOfPassengers) {
		lock.lock();

		try {
			while (this.waitingPassengers() > 0){
				emptyStation.await();
			}

			nrOfPassengersAtStation += nrOfPassengers;
			totalNrOfPassengers += nrOfPassengers;
			System.out.println(nrOfPassengers + " passengers arrived at station");

			busyStation.signalAll();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * Ask for nrOfPassengers Passengers to leave the station
	 *
	 * @param requestedNrOfPassengers
	 * @return number of passengers actually leaving
	 */
	public int leaveStation(int requestedNrOfPassengers) throws InterruptedException {
		lock.lock();

		try {
			while (this.waitingPassengers() <= 0){
				busyStation.await();
			}

			int actuallyLeaving = Math.min(requestedNrOfPassengers, nrOfPassengersAtStation);
			nrOfPassengersAtStation -= actuallyLeaving;

			emptyStation.signalAll();

			return actuallyLeaving;
		} finally {
			lock.unlock();
		}
	}

	public int waitingPassengers() {
		lock.lock();

		try {
			return nrOfPassengersAtStation;
		} finally {
			lock.unlock();
		}
	}

	public void close() {
		isClosed = true;
	}

	public boolean isClosed() {
		return isClosed;
	}

	public int getTotalNrOfPassengers() {
		return totalNrOfPassengers;
	}
}