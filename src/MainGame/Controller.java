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
    private Player rectangle;
    private Image playerSrc = new Image(getClass().getResourceAsStream("../images/copter.png"));

    public Controller() {
        layout = new Group();
        scene = new Scene(layout, 800, 300, Color.BLACK);
        player = new Player();
    }

    public void start(Stage primaryStage) throws Exception {
        player.setSrc(new Image(getClass().getResourceAsStream("../images/copter.png")));
        player.setX(10);
        player.setY(10);
        player.setWidth(10);
        player.setHeight(10);
        player.setSpeed(10);
        layout.getChildren().add(player.getSrc());

        primaryStage.setTitle("Copter");
//        primaryStage.getIcons().add(copterSrc);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
