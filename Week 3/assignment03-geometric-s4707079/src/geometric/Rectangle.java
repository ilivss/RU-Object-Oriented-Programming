package geometric;

/**
 *
 * @author s4707079 & s1037202
 */

public class Rectangle implements Geometric {

    private double x;
    private double y;
    private double width;
    private double height;

    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public double leftBorder() {
        return x;
    }

    @Override
    public double rightBorder() {
        return x + width;
    }

    @Override
    public double bottomBorder() {
        return y;
    }

    @Override
    public double topBorder() {
        return y + height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public int compareTo(Geometric g) {
        return Double.compare(this.getArea(), g.getArea());
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
