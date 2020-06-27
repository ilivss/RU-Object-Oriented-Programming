package mergesort;

import java.util.Arrays;

public class ParallelMergeSort2 {

    private static final int BOUND = 500_000;
    
    public static void sort(int[] array) {
        Sorter s = new Sorter(array);
        s.run();
    }
    
    private static class Sorter implements Runnable {
        
        private final int[] array;

        public Sorter(int[] array) {
            this.array = array;
        }
        
        private static void sort(int[] array) {
            if (array.length > 1) {
                int[] firstHalf = Arrays.copyOf(array, array.length / 2);
                Thread tLeft = new Thread(new Sorter(firstHalf));
                tLeft.start();
                int[] secondHalf = Arrays.copyOfRange(array, array.length / 2, array.length);
                Sorter sRight = new Sorter(secondHalf);
                sRight.run();
                try {
                    tLeft.join();
                } catch (InterruptedException e) {
                    System.err.println("Interrupted!");
                    e.printStackTrace();
                    System.exit(-1);
                }
                MergeSort.merge(firstHalf, secondHalf, array);
            }
        }
        
        @Override
        public void run() {
            if (array.length > BOUND) {
                // split work
                sort(array);
            } else {
                MergeSort.sort(array);
            }
        }
    }
}
