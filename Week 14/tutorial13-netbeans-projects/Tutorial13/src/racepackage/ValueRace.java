/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racepackage;

/**
 *
 * @author pol
 */
public class ValueRace implements Runnable {
    static int value = 0;
    
    public static void main(String[] args) {
        Thread t1 = new Thread(new ValueRace());
        Thread t2 = new Thread(new ValueRace());
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        }
        System.out.println(value + " is not 200000");
    }
    
    public void run() {
        for (int i = 0; i < 100000; ++i) {
            value++;
        }
    }
}
