package taxi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Simulation {

	/**
	 * Constants for the size of the simulation
	 */
	public static final int TRAIN_TRIPS = 10;
	public static final int MIN_TRAVELLERS = 60;
	public static final int MAX_TRAVELLERS = 90;
	public static final int CAPACITY_SMALL = 4;
	public static final int CAPACITY_LARGE = 7;
	public static final int TIME_SMALL = 2;
	public static final int TIME_LARGE = 3;
	public static final int NR_OF_TAXIS = 4;
	public static final int NR_OF_SMALL_TAXIS = 2;

	/**
	 * main elements of the simulation
	 */
	private final Taxi[] taxis;
	private final Train train;
	private final Station station;

	/**
	 * hasEnded: is the simulation finished? nextTaxi: number of the taxi to be use
	 * in next step
	 */
//	private boolean hasEnded = false;
//	private int nextTaxi = 0;

	/**
	 * Constructor: create station and small and large taxis
	 */
	public Simulation() {
		station = new Station();
		taxis = new Taxi[NR_OF_TAXIS];
		for (int i = 0; i < NR_OF_TAXIS; i++) {
			taxis[i] = i < NR_OF_SMALL_TAXIS ? new Taxi(i + 1, CAPACITY_SMALL, TIME_SMALL, station)
					: new Taxi(i + 1, CAPACITY_LARGE, TIME_LARGE, station);
		}
		train = new Train(station);
	}

	/**
	 * simulation step: if there are passengers load them in a taxi, otherwise let a
	 * train bring new passengers, or indicate that simulation stops
	 */
//	private void step() {
//		if (station.waitingPassengers() > 0) {
//			taxis[nextTaxi].takePassengers();
//			nextTaxi = (nextTaxi + 1) % NR_OF_TAXIS;
//		} else if (train.getNrOfTrips() < TRAIN_TRIPS) {
//			train.loadPassengers(Util.getRandomNumber(MIN_TRAVELLERS, MAX_TRAVELLERS));
//			train.unloadPassengers();
//		} else {
//			train.closeStation();
//			hasEnded = true;
//		}
//	}

//	private boolean ended() {
//		return hasEnded;
//	}

	public void start() {
		ExecutorService executor = Executors.newCachedThreadPool();

		executor.execute(train);

		for (int i = 0; i < NR_OF_TAXIS; i++){
			executor.execute(taxis[i]);
		}

		shutdownAndAwait(executor);
	}

	private void shutdownAndAwait(ExecutorService executor){
		executor.shutdown();

			try {
				boolean finished = false;
				do {
					finished = executor.awaitTermination(5, TimeUnit.SECONDS);
					System.out.println((finished ? "executor is terminated." : "waiting for termination..."));
				}  while (!finished);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	public void showStatistics() {
		System.out.println("All persons have been transported");
		System.out.println("Total transport time in this simulation:" + calcTotalTime());
		System.out.println("Total number of train travelers: " + getArrivedPassengerCount());
		System.out.println("Total number of persons transported in this simulation: " + getDepartedPassengerCount());
	}

	/**
	 * Calculates the total time of the simulation by looping over all taxis
	 *
	 * @return total time
	 */
	private int calcTotalTime() {
		int time = 0;
		for (Taxi taxi : taxis) {
			time += taxi.calcTotalTime();
		}
		return time;
	}

	/**
	 * Calculates the total number of passengers that has been transported by
	 * looping over all taxis
	 *
	 * @return total number of passengers
	 */
	public int getDepartedPassengerCount() {
		int total = 0;
		for (Taxi taxi : taxis) {
			total += taxi.getTotalNrOfPassengers();
		}
		return total;
	}

	public int getArrivedPassengerCount() {
		return station.getTotalNrOfPassengers();
	}
}
