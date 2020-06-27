/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hidden;

import drinkersandbar.Drinker;

/**
 *
 * @author Sjaak
 */
public class DrinkerRunner implements Runnable {

    private final Drinker myDrinker;

    public DrinkerRunner(Drinker drinker) {
        this.myDrinker = drinker;
    }

    @Override
    public void run() {
        while( ! myDrinker.isSatisfied() ) {
            myDrinker.drawAndDrink(); // Note: different from tapAndDrink!
        }
    }

}

