package MainGame;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * MainGame.Copter
 * Created by Dan on 2/24/2016.
 */
public class Controller extends Application {

    private Group layout;
    private Scene scene;
    private Player player;
    private double playerX;
    private double playerY;

    private Barrier barrier;

    public Controller() {
        layout = new Group();
        scene = new Scene(layout, 800, 300, Color.BLACK);
        player = new Player(new Image(getClass().getResourceAsStream("../images/copter.png")));
        playerX = 10;
        playerY = 10;
        barrier = new Barrier();
    }

    public void start(Stage primaryStage) throws Exception {
        player.setBounds(playerX, playerY, 50, 50);
        player.setSpeed(10);
        player.getSrc().setPreserveRatio(true);
        player.getSrc().setSmooth(true);
        player.getSrc().setCache(true);
        layout.getChildren().add(player.getSrc());

        barrier.setBounds(100, 100, 20, 200);
        barrier.setFill(Color.LIME);
        layout.getChildren().add(barrier.getSrc());

        primaryStage.setTitle("Copter");
//        primaryStage.getIcons().add(copterSrc);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
