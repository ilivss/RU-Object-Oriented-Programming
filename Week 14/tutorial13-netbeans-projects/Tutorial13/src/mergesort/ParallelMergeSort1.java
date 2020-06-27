package mergesort;

import java.util.Arrays;

public class ParallelMergeSort1 implements Runnable {
    
    public static final int BOUND = 2_500_000;
    public final int[] array;
    
    public ParallelMergeSort1(int[] array) {
        this.array = array;
    }
    
    public static void sort(int[] array) {
        if (array.length > BOUND) {
            // split work with threads
            int[] firstHalf = Arrays.copyOf(array, array.length / 2);
            Thread t1 = new Thread(new ParallelMergeSort1(firstHalf));
            t1.start();
            int[] secondHalf = Arrays.copyOfRange(array, array.length / 2, array.length);
            sort(secondHalf);
            try {
                t1.join();
            } catch (InterruptedException e) {
                System.err.println("Interrupted!");
                e.printStackTrace();
                System.exit(-1);
            }
            merge(firstHalf, secondHalf, array);
        } else {
            MergeSort.sort(array);
        }
    }

    @Override
    public void run() {
        ParallelMergeSort1.sort(this.array);
    }

    /**
     * merge two sorted arrays: O(N)
     *
     * @param part1 a sorted array
     * @param part2 a sorted array
     * @param dest destination, length must be >= part1.length + part2.length
     */
    public static void merge(int[] part1, int[] part2, int dest[]) {
        int part1Index = 0, part2Index = 0, destIndex = 0;
        while (part1Index < part1.length && part2Index < part2.length) {
            if (part1[part1Index] < part2[part2Index]) {
                dest[destIndex++] = part1[part1Index++];
            } else {
                dest[destIndex++] = part2[part2Index++];
            }
        }
        // copy elements when at most one of the parts contains elements
        while (part1Index < part1.length) {
            dest[destIndex++] = part1[part1Index++];
        }
        while (part2Index < part2.length) {
            dest[destIndex++] = part2[part2Index++];
        }
    }

    /**
     * simple check to see if array is nondecreasing
     *
     * @param array
     * @return array is sorted
     */
    public static boolean isSorted(int[] array) {
        int current = array[0];
        for (int i : array) {
            if (i < current) {
                return false;
            } else {
                current = i;
            }
        }
        return true;
    }
}
