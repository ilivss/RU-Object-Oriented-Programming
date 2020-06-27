package supermarket;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

	private static final int NR_OF_CLIENTS = 100;

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newCachedThreadPool();
		Store store = new Store(executor);

		List<Future<Void>> cashiers = store.open();

		List<Customer> customers = IntStream.range(0, NR_OF_CLIENTS).mapToObj(i -> new Customer(i, store))
				.collect(Collectors.toList());
		List<Future<Integer>> customerResults = executor.invokeAll(customers);
		
		int totalItemsSold = customerResults.stream()
											.mapToInt(f -> {
												try {
													return f.get();
												} catch (InterruptedException | ExecutionException e) {
													e.printStackTrace();
													System.err.println("Error!");
													return 0;
												}
											}).sum();

		int totalItemsWanted = customers.stream().mapToInt(Customer::getNumberOfItemsWanted).sum();

		cashiers.stream().forEach(i -> i.cancel(true));

//		for (Future<Void> cashier : cashiers) {
//			cashier.cancel(true);
//		}

		executor.shutdown();




		System.out.println("All customers are done.");
		System.out.println(totalItemsWanted + " items wanted.");
		System.out.println(totalItemsSold + " items sold.");

	}
}
