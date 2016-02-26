package Testing.Movement;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class KeyHeld extends Application {

    @Override
    public void start(Stage primaryStage) {
        SimpleIntegerProperty aCount = new SimpleIntegerProperty(0);
        SimpleIntegerProperty bCount = new SimpleIntegerProperty(0);

        KeyCombination a = new KeyCodeCombination(KeyCode.A);
        KeyCombination b = new KeyCodeCombination(KeyCode.B);

        Label aLabel = new Label();
        Label bLabel = new Label();
        aLabel.textProperty().bind(Bindings.concat("  A:  ", aCount));
        bLabel.textProperty().bind(Bindings.concat("  B:  ", bCount));

        HBox root = new HBox(aLabel, bLabel);
        Scene scene = new Scene(root, 300, 250);

        scene.setOnKeyPressed(ke -> {
            aCount.set(a.match(ke) ? aCount.get() + 1 : 0);
            bCount.set(b.match(ke) ? bCount.get() + 1 : 0);
        });
        scene.setOnKeyReleased(ke -> {
            if (a.match(ke)) {
                aCount.set(0);
            } else if (b.match(ke)) {
                bCount.set(0);
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Key Speed Test");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}