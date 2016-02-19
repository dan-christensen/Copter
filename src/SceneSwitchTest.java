import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * PACKAGE_NAME.Copter
 * Created by Dan on 2/11/2016.
 */
public class SceneSwitchTest extends Application {

    Stage window;
    Scene scene1, scene2;
    Label label1;
    Label label2;
    Button button1;
    Button button2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        label1 = new Label("Scene 1");
        label2 = new Label("Scene 2");
        button1 = new Button("Go To Scene 2");
        button1.setOnAction(actionEvent -> window.setScene(scene2));

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 200, 200);

        button2 = new Button("Go To Scene 1");
        button2.setOnAction(actionEvent -> window.setScene(scene1));

        HBox layout2 = new HBox(20);
        layout2.getChildren().addAll(label2, button2);
        scene2 = new Scene(layout2, 600, 300);

        window.setTitle("Title");
        window.setScene(scene1);
        window.show();
    }
}
