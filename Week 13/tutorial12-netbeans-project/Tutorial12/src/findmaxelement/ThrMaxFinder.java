package findmaxelement;

import java.util.stream.IntStream;

public class ThrMaxFinder implements Runnable {

    public static void main(String[] args) {
        ThrMaxFinder f = new ThrMaxFinder(
                IntStream.concat(
                        IntStream.rangeClosed(0, 1_000_000_000),
                        IntStream.rangeClosed(0, 999_000_000)
                ).toArray(),
                4);
        long time1 = System.currentTimeMillis();
        f.run();
        long time2 = System.currentTimeMillis();
        System.out.println(f.getResult() + " found in " + (time2 - time1) + "ms");
    }

    private final int[] array;
    private final int from, to;   // indices for each thread
    private final int maxThreads; // maximum number of threads
    private int max;

    private ThrMaxFinder(int[] array, int maxThreads, int from, int to) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.maxThreads = maxThreads;
    }

    public ThrMaxFinder(int[] array, int maxThreads) {
        this(array, maxThreads, 0, array.length);
    }

    public int getResult() { // run() has no result, so we need a getter.
        return this.max;
    }

    public int findMax(int from, int to) {
        int m = array[from];
        for (int i = from + 1; i < to; ++i) {
            if (array[i] > m) {
                m = array[i];
            }
        }
        return m;
    }

    @Override
    public void run() {
        if (maxThreads <= 1) {
            this.max = findMax(from, to); // Be careful not to swap these!
        } else { // Split into two threads.
            int mid = (int) (((long) from + (long) to) / 2); // Overflow!
            ThrMaxFinder m1 = new ThrMaxFinder(array, maxThreads / 2, from, mid);
            ThrMaxFinder m2 = new ThrMaxFinder(array, maxThreads / 2, mid, to);
            Thread t1 = new Thread(m1); // Current thread could execute
            Thread t2 = new Thread(m2); // one of these parts instead
            t1.start();
            t2.start();
            try {
                t1.join();
                t2.join();
                // Both threads have finished, now set the maximum of their
                // parent ('this' used for clarity; not required):
                this.max = Math.max(m1.getResult(), m2.getResult());
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.err.println("Thread interrupted, no maximum returned");
            }
        }
    }
}
