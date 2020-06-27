package drinkersandbar;

import java.util.LinkedList;
import java.util.List;

public class Bar {
    private final Tap tap;
    private final List<Glass> glasses;
    
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
        return glasses.remove(0);
    }

    public void drawBeer( Glass glas ) {
        tap.fill(glas);
    }
    
    public void putBack(Glass g) {
        glasses.add(g);
    }
}

