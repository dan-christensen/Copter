package MainGame;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * PACKAGE_NAME.Copter
 * Created by Dan on 2/24/2016.
 */
public class MainGame extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);
    }
}