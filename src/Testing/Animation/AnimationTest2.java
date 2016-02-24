package Testing.Animation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.GroupBuilder;
import javafx.scene.Parent;
import javafx.scene.SceneBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimationTest2 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        DoubleProperty translate = new SimpleDoubleProperty();
        stage.setTitle("Hello JavaFX");
        Rectangle node1 = RectangleBuilder.create()
                .x(0)
                .y(0)
                .width(10)
                .height(10)
                .fill(Color.RED)
                .build();
        node1.translateXProperty().bind(translate);

        Parent parent = GroupBuilder.create()
                .children(node1)
                .translateX(250)
                .translateY(250)
                .build();
        stage.setScene(SceneBuilder.create()
                .width(500)
                .height(500)
                .root(parent)
                .build());
        stage.show();

        TimelineBuilder.create()
                .cycleCount(Timeline.INDEFINITE)
                .keyFrames(
                        new KeyFrame(
                                Duration.seconds(0),
                                new KeyValue(translate, -50)
                        ),
                        new KeyFrame(
                                Duration.seconds(10),
                                new KeyValue(translate, 250)
                        )
                )
                .build().play();
    }
}