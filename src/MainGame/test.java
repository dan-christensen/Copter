package MainGame;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * MainGame.Copter
 * Created by Dan on 3/4/2016.
 */
public class test extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group layout = new Group();
        Scene test = new Scene(layout, 300, 300);

        Actor actor = new Actor();
        actor.setSrc(new Rectangle());
        actor.setBounds(10, 10, 100, 100);
        actor.setFill(Color.LIME);
        actor.setImageSrc(new Image(getClass().getResourceAsStream("../images/copter.png")));
        layout.getChildren().add(actor.getImageSrc());

        primaryStage.setScene(test);
        primaryStage.show();
    }
}
