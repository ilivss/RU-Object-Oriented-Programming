package findmaxelement;

import java.util.stream.IntStream;

public class StrMaxFinder implements Runnable {
    public static void main(String[] args) {
        StrMaxFinder f = new StrMaxFinder(
                IntStream.concat(
                    IntStream.rangeClosed(0, 1_000_000_000),
                    IntStream.rangeClosed(0, 999_000_000)
                ).toArray()); // No instruction on max nr. Threads
        long time1 = System.currentTimeMillis();
        f.run();
        long time2 = System.currentTimeMillis();
        System.out.println("f ran in " + (time2 - time1) + "ms");
    }
    
    private final int[] array;
    private int max;    // No from, to, or maxThreads.
    
    public StrMaxFinder(int[] array) {
        this.array = array;
    }
        
    @Override
    public void run() {
        IntStream.of(array)
                 //.parallel()
                 .max()
                 .ifPresentOrElse(
                         System.out::println,
                         () -> System.out.println("Empty array")
                 );
    }
}
