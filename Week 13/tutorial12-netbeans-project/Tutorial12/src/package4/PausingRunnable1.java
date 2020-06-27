package package4;

import java.util.Date;

public class PausingRunnable1 implements Runnable {
    public static void main(String[] args) {
        Thread t = new Thread(new PausingRunnable1());
        t.start();
    }
    
    @Override
    public void run() {
        while(true) {
            System.out.println(new Date().toString());
            sleep(1000);
        }
    }
}
