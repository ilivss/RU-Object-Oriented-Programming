package mergesort;

import java.util.Arrays;

public class ParallelMergeSort implements Runnable {
    private int[] array;
    private static final int THRESHOLD = 1_500_000; // Overhead-limiting

    public ParallelMergeSort (int[] array){
        this.array = array;
    }

    @Override
    public void run() {

        if (array.length > THRESHOLD) {
            int [] firstHalf = Arrays.copyOf(array, array.length / 2);
            // New thread for each half
            ParallelMergeSort first = new ParallelMergeSort(firstHalf);
            Thread t1 = new Thread(first);
            t1.start();

            int [] secondHalf = Arrays.copyOfRange(array, array.length / 2, array.length);
            ParallelMergeSort second = new ParallelMergeSort(secondHalf);
            second.run();

            // Join
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            merge(firstHalf, secondHalf, array);
        } else {
            MergeSort.sort(array);
        }
    }


    public static void merge(int [] part1, int [] part2, int dest[]) {
        int part1Index = 0, part2Index = 0, destIndex = 0;
        while (part1Index < part1.length && part2Index < part2.length) {
            if (part1[part1Index] < part2[part2Index])
                dest[destIndex ++] = part1[part1Index ++];
            else
                dest[destIndex ++] = part2[part2Index ++];
        }
        // copy elements when at most one of the parts contains elements
        while (part1Index < part1.length)
            dest[destIndex ++] = part1[part1Index ++];
        while (part2Index < part2.length)
            dest[destIndex ++] = part2[part2Index ++];
    }
}
