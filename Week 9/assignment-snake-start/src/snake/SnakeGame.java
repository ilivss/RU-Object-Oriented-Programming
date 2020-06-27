package snake;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * A JavaFX Pane that displays the snake game represented by the given world
 */
public class SnakeGame extends Pane {

    public static final int SCALE = 16;

    public SnakeGame(World world) {
        setPrefSize(world.getSize() * SCALE, world.getSize() * SCALE);
     
        // TODO: Implement graphics
        // Snakehead
        Rectangle head = new Rectangle(world.getSize()/2, world.getSize()/2, 1 * SCALE, 1 * SCALE);
        head.setFill(Color.RED);
        head.xProperty().bind(world.getSnake().getXProperty().multiply(SCALE));
        head.yProperty().bind(world.getSnake().getYProperty().multiply(SCALE));
        
        this.getChildren().add(head);
        
        // Food
        Circle food = new Circle(0 , 0, .5 * SCALE);
        food.setFill(Color.BLUE);
        food.centerXProperty().bind(world.getFood().getXProperty().multiply(SCALE).add(SCALE/2));
        food.centerYProperty().bind(world.getFood().getYProperty().multiply(SCALE).add(SCALE/2));
        
        world.moveFoodRandomly();
        
        this.getChildren().add(food);
        
        
        world.getSnake().addListener(s->{
            Rectangle seg = new Rectangle(100,100, 1 * SCALE, 1 * SCALE);
            seg.setFill(Color.BLACK);
            seg.xProperty().bind(s.getXProperty().multiply(SCALE));
            seg.yProperty().bind(s.getYProperty().multiply(SCALE));
            this.getChildren().add(seg);
        });
    }

    public static Pane createUserInterface(World world) {
        VBox ui = new VBox();

        Label scoreText = new Label();
        Label runningText = new Label("Press 's' to start");

        // TODO: Implement user interface
        scoreText.textProperty().bind(world.getScoreProperty().asString("Score: %d"));
        world.getRunningProperty().addListener(
        (observableValue, aBoolean, t1) -> {
            if (world.isRunning()){
                runningText.setText("Running");
            } else {
                runningText.setText("Paused");
            }
        });

        ui.getChildren().addAll(scoreText, runningText);

        return ui;
    }
}
