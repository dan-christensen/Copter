package Testing;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * PACKAGE_NAME.Copter
 * Created by Dan on 2/11/2016.
 */
public class Main extends Application {

    Button button;
    StackPane layout;
    Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Title");
        button = new Button("Button");
        button.setOnAction(actionEvent -> System.out.println("Button Clicked"));

        layout = new StackPane();
        layout.getChildren().add(button);

        scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}