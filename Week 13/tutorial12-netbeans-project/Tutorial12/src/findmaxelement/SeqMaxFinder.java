package findmaxelement;

import java.util.stream.IntStream;

public class SeqMaxFinder {
    public static void main(String[] args) {
        SeqMaxFinder f = new SeqMaxFinder(
                IntStream.concat(
                    IntStream.rangeClosed(0, 1_000_000_000),
                    IntStream.rangeClosed(0, 999_000_000)
                ).toArray());
        long time1 = System.currentTimeMillis();
        int m = f.findMax();
        long time2 = System.currentTimeMillis();
        System.out.println(m + " found in " + (time2 - time1) + "ms");
    }
    
    private final int[] array;

    public SeqMaxFinder(int[] array) {
        this.array = array;
    }
    
    public int findMax() {
        int m = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (array[i] > m) {
                m = array[i];
            }
        }
        return m;
    }
}
