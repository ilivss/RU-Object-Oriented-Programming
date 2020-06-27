package taxi;

/**
 * Trains bring a number of passengers to a station in the Taxi simulation
 */
public class Train implements Runnable {
	private int nrOfPassengers;
	private final Station station;
	private int nrOfTrips = 0;

	public Train(Station station) {
		this.station = station;
		this.nrOfPassengers = 0;
	}

	/**
	 * Populate this train with number nrOfPassengers
	 *
	 * @param number the number of passengers of this train
	 */
	public void loadPassengers(int number) {
		nrOfPassengers = number;
	}

	/**
	 * empties this train and augment the number of Passengers at the station with
	 * this nrOfPassenegers
	 */
	public void unloadPassengers() {
		nrOfTrips += 1;
		station.enterStation(nrOfPassengers);
	}

	private void closeStation() {
		station.close();
	}

	public int getNrOfTrips() {
		return nrOfTrips;
	}

	@Override
	public void run() {
		while (nrOfTrips < Simulation.TRAIN_TRIPS){
			if (station.waitingPassengers() <= 0){
				loadPassengers(Util.getRandomNumber(Simulation.MIN_TRAVELLERS, Simulation.MAX_TRAVELLERS));
				unloadPassengers();
			}
		}

		closeStation();
		System.out.println(String.format("%d trips made. Closing station.", nrOfTrips));
	}
}