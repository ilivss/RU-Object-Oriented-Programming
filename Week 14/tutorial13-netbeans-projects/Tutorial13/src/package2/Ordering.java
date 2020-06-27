package package2;

public class Ordering {
    public static void main(String[] args) {
        new Ordering();
    }
    
    public Ordering() {
        int num = 7;
        Thread a = new Thread(new Print('a', num));
        Thread b = new Thread(new Print('b', num));
        a.run();
        b.run();
    }
    
    private class Print implements Runnable {
        private final char c;
        private final int n;
        
        public Print(char c, int n) {
            this.c = c;
            this.n = n;
        }
        
        @Override
        public void run() {
            for (int i = 0; i < n; ++i) {
                System.out.print(c);
            }
        }
    }
}
