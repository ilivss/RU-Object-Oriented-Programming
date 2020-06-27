package drinkersandbar;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bar {
    private final Tap tap;
    private final List<Glass> glasses;
    private final Lock lock = new ReentrantLock();
    private final Condition glassReturned = lock.newCondition();
    
    public Bar( int numberOfGlasses ) {
        tap = new Tap();
        glasses = new LinkedList<>();
        lock.lock();
        try {
            for (int i = 0; i < numberOfGlasses; i++) {
                glasses.add(new Glass());
            }
        } finally {
            lock.unlock();
        }
    }
    
    public boolean glassesLeft() {
        lock.lock();
        try {
            return glasses.size() > 0;
        } finally {
            lock.unlock();
        }
    }

    public Glass getGlass() throws InterruptedException {
        lock.lock();
        try {
            while (!glassesLeft()) {
                glassReturned.await();
            }
            return glasses.remove(0);
        } finally {
            lock.unlock();
        } 
    }

    public void drawBeer( Glass glas ) {
        tap.fill(glas);
    }
    
    public void putBack(Glass g) {
        lock.lock();
        try {
            glasses.add(g);
            glassReturned.signalAll();
        } finally {
            lock.unlock();
        } 
    }
}

