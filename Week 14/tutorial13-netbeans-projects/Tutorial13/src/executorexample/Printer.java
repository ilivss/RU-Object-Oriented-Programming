package executorexample;

import java.util.Random;

public class Printer implements Runnable {

    String string;
    int count;
    Random rand;

    public Printer(String string, int count) {
        this.string = string;
        this.count = count;
        this.rand = new Random();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < count; i += 1) {
                System.out.print(string + i + " ");
                Thread.sleep(rand.nextInt(100));
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted while printing " + string);
        }
        System.out.println(System.lineSeparator() + string + " is done.");
    }
}
