package package3;

public class ImplementingRunnable4 implements Runnable {
    public static void main(String[] args) {
        Thread t = new Thread(new ImplementingRunnable4());
        t.start();
        t.start();
    }
    
    @Override
    public void run() {
        System.out.println("Does this work?");
    }
}
