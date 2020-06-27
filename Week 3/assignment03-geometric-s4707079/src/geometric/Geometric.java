package geometric;

/**
 *
 * @author s4707079 & s1037202
 */

public interface Geometric extends Comparable<Geometric> {

    public double leftBorder ();

    public double rightBorder ();

    public double bottomBorder ();

    public double topBorder ();

    public double getArea ();

    public void move (double dx, double dy);

    public int compareTo(Geometric g);

}
