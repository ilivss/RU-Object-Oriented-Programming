package hidden;

import drinkersandbar.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bar {
    private final Tap tap;
    private final List<Glass> glasses;
    private Lock myLock = new ReentrantLock();
    private Condition newGlass = myLock.newCondition();
    
    public Bar( int numberOfGlasses ) {
        tap = new Tap();
        glasses = new LinkedList<>();
        for (int i = 0; i < numberOfGlasses; i++) {
            glasses.add(new Glass());
        }
    }
    
    public boolean glassesLeft() {
        return glasses.size() > 0;
    }

    public Glass getGlass() throws InterruptedException {
        myLock.lock();
        try {
            while ( glasses.isEmpty() ) { // await in a loop checking the
                newGlass.await();         // requirement!
            }
            return glasses.remove(0);
        } finally {
            myLock.unlock();
        }
    }

    public void drawBeer( Glass glas ) {
        tap.fill(glas);
    }
    
    public void putBack(Glass g) {
        myLock.lock();
        try {
            glasses.add(g);
            newGlass.signalAll();
        } finally {
            myLock.unlock();
        }
    }
}

