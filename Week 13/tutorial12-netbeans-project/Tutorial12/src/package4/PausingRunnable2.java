package package4;

import java.util.Date;

public class PausingRunnable2 implements Runnable {
    public static void main(String[] args) {
        Thread t = new Thread(new PausingRunnable2());
        t.start();
    }
    
    @Override
    public void run() {
        while(true) {
            System.out.println(new Date().toString());
            Thread.sleep(1000);
        }
    }
}
