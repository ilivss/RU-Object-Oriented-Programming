package package4;

import java.util.Date;

public class PausingRunnable4 implements Runnable {
    public static void main(String[] args) {
        Thread t = new Thread(new PausingRunnable4());
        t.start();
    }
    
    @Override
    public void run() {
        while(true) {
            System.out.println(new Date().toString());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Sleeping interrupted");
            }
        }
    }
}
