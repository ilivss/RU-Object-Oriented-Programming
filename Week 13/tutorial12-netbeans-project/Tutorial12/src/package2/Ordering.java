package package2;

public class Ordering {
    public static void main(String[] args) {
        new Ordering();
    }
    
    public Ordering() {
        int num = 7;
        Print a = new Print('a', num);
        Print b = new Print('b', num);
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
