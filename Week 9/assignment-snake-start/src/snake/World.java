package snake;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

/**
 * World keeps track of the state of a snake game
 */
public class World {

    public final static int DELAY = 200;

    private final int size;

    private final Snake snake;
    private final Food food;

    private final Random random = new Random();

    private final BooleanProperty running = new SimpleBooleanProperty(false);

    private final IntegerProperty score = new SimpleIntegerProperty(0);

    public World(int size) {
        this.size = size;

        snake = new Snake(size / 2, size / 2, this);
        food = new Food();

        // TODO: Implement timeline
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(DELAY), e->snake.move()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        
        running.addListener(
        (observableValue, aBoolean, t1) -> {
            if (t1) {
                timeline.play();
            } else {
                timeline.pause();
            }        
        });
        
        moveFoodRandomly();
    }

    public void moveFoodRandomly() {
        do {
            food.moveTo(random.nextInt(size), random.nextInt(size));
        } while (snake.isAt(food.getX(), food.getY()));
    }

    public void endGame() {
        running.set(false);
    }

    public void setRunning(boolean running) {
        this.running.set(running);
    }

    public void setScore(int score) {
        this.score.set(score);
    }

    public boolean isRunning() {
        return running.get();
    }

    public int getSize() {
        return size;
    }

    public int getScore() {
        return score.get();
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }

    public BooleanProperty getRunningProperty() {
        return running;
    }

    public IntegerProperty getScoreProperty() {
        return score;
    }
}
