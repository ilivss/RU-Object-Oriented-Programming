package geometric;

import java.util.Comparator;

/**
 *
 * @author s4707079 & s1037202
 */

public class XComparator implements Comparator<Geometric> {

    @Override
    public int compare(Geometric g1, Geometric g2) {
        return Double.compare(g1.leftBorder(), g2.leftBorder());
    }
}
