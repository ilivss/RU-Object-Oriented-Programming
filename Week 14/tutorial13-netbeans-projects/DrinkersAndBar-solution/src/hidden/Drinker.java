package hidden;

import drinkersandbar.*;
import static drinkersandbar.Util.randomInRange;
import static drinkersandbar.Util.takeABreak;
import static drinkersandbar.Util.takeAnExactBreak;

public class Drinker implements Runnable {
    
    private static final int DRINK_TIME_PER_CC     = 5;
    private static final int MIN_NUMBER_OF_GLASSES = 2;
    private static final int DIGEST_TIME   = 10;
    private static final int WAIT_A_LITTLE = 50;

    private int nrOfGlasses;
    private final Bar myBar;
    private final int myId;
     
    public Drinker( int id, Bar bar ) {
        this.myId = id;
        this.myBar = bar;
        this.nrOfGlasses = randomInRange( MIN_NUMBER_OF_GLASSES, MIN_NUMBER_OF_GLASSES + 5);
        System.out.println("Drinker " + id + " is going to drink " + nrOfGlasses + " glasses");
    }

    public void drawAndDrink() {
        try {
            Glass glass = myBar.getGlass();
            myBar.drawBeer(glass);
            takeAnExactBreak( glass.getVolume() * DRINK_TIME_PER_CC );
            glass.empty();
            System.out.println("Drinker " + myId + " drinks a glass of beer.");
            nrOfGlasses--;
            takeABreak(WAIT_A_LITTLE);
            myBar.putBack(glass);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public boolean isSatisfied() {
        return nrOfGlasses == 0;
    }
    
    @Override
    public void run() {
        while( ! isSatisfied() ) {
            drawAndDrink();
        }
    }
}
