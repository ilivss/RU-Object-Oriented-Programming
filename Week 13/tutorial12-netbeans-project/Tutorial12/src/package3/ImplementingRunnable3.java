package package3;

public class ImplementingRunnable3 implements Runnable {
    public static void main(String[] args) {
        Thread t = new Thread(new ImplementingRunnable3());
        t.start();
    }
    
    @Override
    public void run() {
        System.out.println("Does this work?");
    }
}
