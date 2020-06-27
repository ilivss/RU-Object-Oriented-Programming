package findarrayelement;

import java.util.stream.IntStream;

public class ThreadedFinder implements Runnable {
    
    private static long TIME1;
    public static void main(String[] args) {
        ThreadedFinder f = new ThreadedFinder(
                IntStream.concat(
                    IntStream.range(0, 750_000_000),
                    IntStream.range(0, 750_000_000)
                ).toArray(),
                400_000_000);
        TIME1 = System.currentTimeMillis();
        f.run();    // Do we need a thread here?
    }
    
    
    private final int[] array;
    private final int goal, from, to;
    private final int THRESHOLD = 1_000_000_000; // Overhead-limiting, guess a good value

    private ThreadedFinder(int[] array, int goal, int from, int to) {
        this.array = array;
        this.goal = goal;
        this.from = from;
        this.to = to;
    }

    public ThreadedFinder(int[] array, int goal) {
        this(array, goal, 0, array.length);
    }
    
    @Override
    public void run() {
        if (to - from <= THRESHOLD) { // Do all remaining work in this thread
            for (int i = from; i < to; ++i) {
                if (i % 1000000 == 0) {
                    System.out.println("Inspecting " + i);
                }
                if (array[i] == goal) {
                    long time2 = System.currentTimeMillis();
                    System.out.println(goal + " found at index " + i +
                            " after " + (time2 - TIME1) + "ms");
                    System.exit(0); // Optional, to stop all threads
                }
            }
        } else {
            int mid = (int) (((long) from + (long) to) / 2); // overflow!;
            ThreadedFinder f1 = new ThreadedFinder(array, goal, from, mid);
            ThreadedFinder f2 = new ThreadedFinder(array, goal, mid, to);
            new Thread(f1).start();
            new Thread(f2).start(); // Why only 2 subtasks?
        } // end of run(); thread executing this split terminates!
    }
}
