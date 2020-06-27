package snake;

import java.util.LinkedList;
import java.util.List;

/**
 * Snake consists of segments, where this head segment keeps track of the other body segments
 */
public class Snake extends Segment {

    public interface SnakeSegmentListener {
        public void onNewSegment(Segment segment);
    }

    private Direction direction = Direction.RIGHT;

    private final World world;

    private final List<Segment> body = new LinkedList<>();

    private final List<SnakeSegmentListener> listeners = new LinkedList<>();

    public Snake(int x, int y, World world) {
        super(x, y);
        this.world = world;
    }

    public void move() {
        int newX = getX() + direction.getDX();
        int newY = getY() + direction.getDY();
        
        // TODO: Implement movement       
        if (this.isAt(newX,newY)){
            world.endGame();
        } else if (newX < 0 || newY < 0 || newX >= world.getSize() || newY >= world.getSize()){
            world.endGame();
        } else if (world.getFood().getX() == newX && world.getFood().getY() == newY) {
            this.addNewSegment(newX, newY);
            world.moveFoodRandomly();
            world.setScore(world.getScore()+1);
        } else {
            if (!body.isEmpty()){
                Segment tail = body.remove(0);                
                body.add(tail);             
                tail.setPosition(getX(), getY());
                this.setPosition(newX, newY);
            } else {
                this.setPosition(newX, newY);
            }
        }
    }
    
    private void addNewSegment (int x, int y){
        Segment newSeg = new Segment(getX(), getY());
        
        for (SnakeSegmentListener list : listeners){
            list.onNewSegment(newSeg);
        }
        
        body.add(newSeg);
        this.setPosition(x, y);
    }
    
    public void addListener(SnakeSegmentListener listener) {
        listeners.add(listener);
    }

    public void setDirection(Direction newDirection) {
        direction = newDirection;
    }

    public boolean isAt(int x, int y) {
        for (Segment segment : body) {
            if (segment.getX() == x && segment.getY() == y) {
                return true;
            }
        }

        return false;
    }

    public Direction getDirection() {
        return direction;
    }
}
