package findarrayelement;

import java.util.stream.IntStream;

public class StreamFinder implements Runnable {
    public static void main(String[] args) {
        StreamFinder f = new StreamFinder(
                IntStream.concat(
                    IntStream.range(0, 750_000_000),
                    IntStream.range(0, 750_000_000)
                ).toArray(),
                400_000_000);
        long time1 = System.currentTimeMillis();
        f.run();
        long time2 = System.currentTimeMillis();
        System.out.println("f ran in " + (time2 - time1) + "ms");
    }
    
    private final int[] array;
    private final int goal;    // No from, to, or THRESHOLD.
    
    public StreamFinder(int[] array, int goal) {
        this.array = array;
        this.goal = goal;
    }
        
    @Override
    public void run() {
        IntStream.range(0, array.length)
                 //.parallel()
                 .mapToObj(i -> new int[] {i, array[i]}) // Finding indices in streams is hard, so poor-man's zipping is used!
                 //.parallel()
                 .filter(p -> p[1] == goal)
                 .findFirst()  // Can also use findAny()
                 .ifPresentOrElse(
                         p -> System.out.println(
                                 p[1] + " found at index " + p[0]),
                         () -> System.out.println("No such element")
                 );
    }
}
