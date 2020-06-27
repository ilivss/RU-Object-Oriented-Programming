package package4;

import java.util.Date;

public class PausingRunnable3 implements Runnable {
    public static void main(String[] args) {
        Thread t = new Thread(new PausingRunnable3());
        t.start();
    }
    
    @Override
    public void run() throws InterruptedException {
        while(true) {
            System.out.println(new Date().toString());
            Thread.sleep(1000);
        }
    }
}
