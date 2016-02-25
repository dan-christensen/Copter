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

    Group layout;
    Scene scene;
    Player player;
    Player rectangle;
    private Image playerSrc = new Image(getClass().getResourceAsStream("../images/copter.png"));

    public Controller() {
        layout = new Group();
        scene = new Scene(layout, 800, 300, Color.BLACK);
        player = new Player();
    }

    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Copter");
//        primaryStage.getIcons().add(copterSrc);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
