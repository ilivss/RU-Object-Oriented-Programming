package package1;

public class Tutorial12 implements Runnable {

    public static void main(String[] args) {
        Thread t = new Thread(this);  // What is wrong here?
        t.start();
    }

    @Override    
    public void run() {
        System.out.println("test");
    }
}
