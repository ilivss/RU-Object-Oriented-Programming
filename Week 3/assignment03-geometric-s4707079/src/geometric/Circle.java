package geometric;

public class Circle implements Geometric {

    private double x;
    private double y;
    private double radius;

    public Circle(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public double leftBorder() {
        return x - radius;
    }

    @Override
    public double rightBorder() {
        return x + radius;
    }

    @Override
    public double bottomBorder() {
        return y - radius;
    }

    @Override
    public double topBorder() {
        return y + radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    @Override
    public String toString() {
        return "Circle\t{" +
                "x=" + x +
                ", y=" + y +
                ", radius=" + radius +
                '}';
    }

    @Override
    public int compareTo(Geometric g) {
        return Double.compare(this.getArea(), g.getArea());
    }

}
