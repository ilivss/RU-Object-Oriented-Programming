package geometric;

import java.util.Arrays;
import java.util.InputMismatchException;

/**
 *
 * @author s4707079 & s1037202
 */

public class Model {

    private Geometric[] geometric_objects;

    public Model(int size) {
        this.geometric_objects = new Geometric[size];
    }

    public Geometric[] getGeometric_objects() {
        return geometric_objects;
    }

    public void quit (){
        System.exit(0);
    }

    public String show () {
        StringBuilder sb = new StringBuilder();
        int i = 0;

        for (Geometric g : geometric_objects) {
            // add check for null items.
            if (g == null){
                sb.append(i++).append(". ").append("No geometric object").append("\n");

            }
            else {
                sb.append(i++).append(". ").append(g.toString()).append("\n");
            }
        }

        sb.append('\n');

        return sb.toString();
    }

    public void addCircle (double x, double y, double r) {
        int i = 0;
        while (geometric_objects[i] != null){
            i++;
        }
        geometric_objects[i] = new Circle(x,y,r);
    }

    public void addRectangle (double x, double y, double w, double h) {
        int i = 0;
        while (geometric_objects[i] != null){
            i++;
        }
        geometric_objects[i] = new Rectangle(x, y, w, h);
    }

    public void move (int i, double dx, double dy) {
        geometric_objects[i].move(dx, dy);
    }

    public void remove (int i){
        if (0 < i && i < geometric_objects.length){
            geometric_objects[i] = null;
            shiftleft(i);
        } else{
            throw new InputMismatchException("index must be < " + geometric_objects.length + " and > 0");
        }
    }

    private void shiftleft(int i){
        for (int j= i+1; j < geometric_objects.length; j++){
            if (geometric_objects[j] != null){
                geometric_objects[j-1] = geometric_objects[j];      // copy item to previous index (which is null).
                geometric_objects[j] = null;                        // Removing the copied element at current index.
            }
        }
    }

    public void sort (char c){
        int i = 0;
        while (geometric_objects[i] != null) {                      // Count number of elements in array.
            i++;
        }

        switch (c){
            case 'x': Arrays.sort(geometric_objects, 0, i, new XComparator()); break;
            case 'y': Arrays.sort(geometric_objects, 0, i, new YComparator()); break;
        }
    }

    public void sort () {
        int i = 0;
        while (geometric_objects[i] != null) {
            i++;
        }
        Arrays.sort(geometric_objects, 0, i);
    }
}