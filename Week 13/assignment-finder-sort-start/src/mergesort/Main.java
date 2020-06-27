/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mergesort;

import java.util.Arrays;

public class Main {

    private static final int L = 10_000_000;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      long time1, time2;
      int [] a1 = new int[L];
      int [] a2 = new int[L];
      for (int i = 0; i < L; i += 1) {
        a1[i] = a2[i] = (int) (Math.random() * L);
      }
      System.out.print("sequential sort: ");
      time1 = System.currentTimeMillis();
      MergeSort.sort(a1);
      time2 = System.currentTimeMillis();
      System.out.println((time2 - time1) + " millis. Array is sorted = " + MergeSort.isSorted(a1));

      System.out.print("parrallel sort: ");
      time1 = System.currentTimeMillis();
      ParallelMergeSort test = new ParallelMergeSort(a2);
      test.run();
      time2 = System.currentTimeMillis();
      System.out.println((time2 - time1) + " millis. Array is sorted = " + MergeSort.isSorted(a2));
      System.out.println("sorter arrays equals = " + Arrays.equals(a1, a2));
      System.out.println("The number of Processors is " + Runtime.getRuntime().availableProcessors());
    }

  /**
   * I got the most consistent and lowest result when using a Threshold of 1_500_000.
   * The results I got are:
   *  parrallel sort: 552 millis. Array is sorted = true
   *  parrallel sort: 561 millis. Array is sorted = true
   *  parrallel sort: 586 millis. Array is sorted = true
   *  parrallel sort: 553 millis. Array is sorted = true
   *  parrallel sort: 539 millis. Array is sorted = true
   *
   * The number of Processors is 8
   *
   */
}
