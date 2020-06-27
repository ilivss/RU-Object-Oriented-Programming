package taxi;

public class Taxi implements Runnable {

	private final int taxiId;
	private final int maxNrOfPassengers;
	private final int transportationTime;
	private final Station station;

	private int totalNrOfPassengers = 0;
	private int nrOfRides = 0;

	public Taxi(int nr, int maxNumberOfPassengers, int transportationTime, Station station) {
		this.taxiId = nr;
		this.maxNrOfPassengers = maxNumberOfPassengers;
		this.transportationTime = transportationTime;
		this.station = station;
		System.out.println("Taxi " + nr + " created");
	}

	/**
	 * Try to take the maximum number of passengers from the station. If actual
	 * number op passengers is less then that number is taken When there are no
	 * passengers the taxi just waits a little
	 */
	public void takePassengers() {
		try {
			int nrOfPassengers = station.leaveStation(maxNrOfPassengers);
			if (nrOfPassengers > 0) {
				totalNrOfPassengers += nrOfPassengers;
				nrOfRides++;
				System.out.println("Taxi " + taxiId + " takes " + nrOfPassengers + " passengers");
			} else {
				System.out.println("There are no passengers for taxi " + taxiId);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Calculates the total time of this taxi by multiplying the number of rides by
	 * the transportation time
	 * 
	 * @return total time
	 */
	public int calcTotalTime() {
		return transportationTime * nrOfRides;
	}

	public int getTotalNrOfPassengers() {
		return totalNrOfPassengers;
	}

	@Override
	public void run() {
		while (station.waitingPassengers() > 0 || !station.isClosed()){
			try {
				this.takePassengers();
				Thread.sleep(transportationTime*100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}