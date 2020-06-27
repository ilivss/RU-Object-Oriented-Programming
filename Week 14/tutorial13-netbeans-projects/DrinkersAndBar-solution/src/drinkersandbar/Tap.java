package drinkersandbar;

import static drinkersandbar.Util.takeAnExactBreak;

public class Tap {

  private static final int CC = 200;
  private static final int DRAW_TIME_PER_CC = 1;

    public void fill( Glass glass ) {
        glass.fill( CC );
        takeAnExactBreak( glass.getVolume() * DRAW_TIME_PER_CC );
    }
}
