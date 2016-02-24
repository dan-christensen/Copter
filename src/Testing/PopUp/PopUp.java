package Testing.PopUp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * PACKAGE_NAME.Copter
 * Created by Dan on 2/11/2016.
 */
public class PopUp extends Application {

    Scene scene;
    Button button;
    VBox layout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        button = new Button("Open New Window");
        button.setOnAction(e -> {
            boolean result = ConfirmBox.display("Confirm?", "Test Popup");
            if (result) {
                System.out.println("Yes");
            }else {
                System.out.println("NO");
            }
        });
        layout = new VBox(20);
        layout.getChildren().add(button);
        scene = new Scene(layout, 300, 250);
        primaryStage.setTitle("Title");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
