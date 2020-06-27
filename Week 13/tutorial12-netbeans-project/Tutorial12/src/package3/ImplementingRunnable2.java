package package3;

public class ImplementingRunnable2 implements Runnable {
    public static void main(String[] args) {
        Runnable t = new ImplementingRunnable2();
        t.start();
    }
    
    @Override
    public void run() {
        System.out.println("Does this work?");
    }
}
