package executorexample;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new Printer("A", 50)); // Adding tasks
        executor.execute(new Printer("B", 42)); // Adding more tasks
        Future<?> fC = executor.submit(new Printer("C", 7));
        // Submission also works
        //shutdownAndBusyWait(executor);
        //shutdownAndAwait(executor);
        shutdownNowAndAwait(executor);
        try {
            System.out.println("The null result of Printer C: " + fC.get());
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Future.get was interrupted");
        }
    }

    private static void shutdownAndBusyWait(ExecutorService executor) {
        executor.shutdown();    // Stop adding tasks
        // Don't use shutdownNow here, we do want
        // to wait for normal termination.
        try {
            while (!executor.isTerminated()) {
                System.out.println(System.lineSeparator()
                        + "waiting for termination...");
                Thread.sleep(1000);
            }
            System.out.println("executor is terminated.");
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        }
    }

    private static void shutdownAndAwait(ExecutorService executor) {
        executor.shutdown();
        try {
            boolean finished = false;
            do {
                finished = executor.awaitTermination(500, TimeUnit.MILLISECONDS);
                System.out.println(System.lineSeparator() + (finished
                        ? "executor is terminated."
                        : "waiting for termination..."));
            } while (!finished);
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        }
    }

    private static void shutdownNowAndAwait(ExecutorService executor) {
        executor.shutdownNow();
        try {
            boolean finished = false;
            do {
                finished = executor.awaitTermination(10, TimeUnit.MILLISECONDS);
                System.out.println(System.lineSeparator() + (finished
                        ? "executor is terminated."
                        : "waiting for termination..."));
            } while (!finished);
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        }
    }
}
