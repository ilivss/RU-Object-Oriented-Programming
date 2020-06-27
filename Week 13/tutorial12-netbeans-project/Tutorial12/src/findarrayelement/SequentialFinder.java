package findarrayelement;

import java.util.stream.IntStream;

public class SequentialFinder {

    public static void main(String[] args) {
        SequentialFinder f = new SequentialFinder(
                IntStream.concat(
                        IntStream.range(0, 750_000_000),
                        IntStream.range(0, 750_000_000)
                ).toArray(),
                400_000_000);
        long time1 = System.currentTimeMillis();
        f.find();
        long time2 = System.currentTimeMillis();
        System.out.println("find ran in " + (time2 - time1) + "ms");
    }

    private final int[] array;
    private final int goal;

    public SequentialFinder(int[] array, int goal) {
        this.array = array;
        this.goal = goal;
    }

    public void find() {
        for (int i = 0; i < array.length; ++i) {
            if (i % 1000000 == 0) {
                System.out.println("Inspecting " + i);
            }
            if (array[i] == goal) {
                System.out.println(goal + " found at index " + i);
                return;
            }
        }
    }
}
