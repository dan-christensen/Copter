/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing.Animation;

import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.animation.TranslateTransitionBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.LinearGradientBuilder;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author DellPC
 */
public class JavaFX_12 extends Application {
    final Image DOG_1 = new Image(getClass().getResourceAsStream("copterA1.png"));
    final Image DOG_2 = new Image(getClass().getResourceAsStream("copterA2.png"));
    final Image DOG_3 = new Image(getClass().getResourceAsStream("copterA3.png"));
    final Image DOG_4 = new Image(getClass().getResourceAsStream("copterA4.png"));
    final Image DOG_5 = new Image(getClass().getResourceAsStream("copterA3.png"));
    final Image DOG_6 = new Image(getClass().getResourceAsStream("copterA2.png"));
    /*    final Image DOG_7 =  new Image(getClass().getResourceAsStream("copterA1.png"));
        final Image DOG_8 =  new Image(getClass().getResourceAsStream("copterA1.png"));
        final Image DOG_9 =  new Image(getClass().getResourceAsStream("copterA1.png"));*/
    final Image BG = new Image(getClass().getResourceAsStream("bg.jpg"));
    private final Random rand = new Random();

    private Animation walk;
    private Group dog;

    @Override
    public void start(Stage primaryStage) {
        final ImageView dog1 = new ImageView(DOG_1);
        final ImageView dog2 = new ImageView(DOG_2);
        final ImageView dog3 = new ImageView(DOG_3);
        final ImageView dog4 = new ImageView(DOG_4);
        final ImageView dog5 = new ImageView(DOG_5);
        final ImageView dog6 = new ImageView(DOG_6);
/*        final ImageView dog7 = new ImageView(DOG_7);
        final ImageView dog8 = new ImageView(DOG_8);
        final ImageView dog9 = new ImageView(DOG_9);*/
        final ImageView bg = new ImageView(BG);

        dog = new Group(dog1);
        dog.setTranslateX(300);
        dog.setTranslateY(450);

        TimelineBuilder.create()
                .cycleCount(Animation.INDEFINITE)
                .keyFrames(
                        new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t) {
                                dog.getChildren().setAll(dog1);
                            }
                        }),
                        new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t) {
                                dog.getChildren().setAll(dog2);
                            }
                        }),
                        new KeyFrame(Duration.millis(300), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t) {
                                dog.getChildren().setAll(dog3);
                            }
                        }),
                        new KeyFrame(Duration.millis(400), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t) {
                                dog.getChildren().setAll(dog4);
                            }
                        }),
                        new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t) {
                                dog.getChildren().setAll(dog5);
                            }
                        }),
                        new KeyFrame(Duration.millis(600), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t) {
                                dog.getChildren().setAll(dog6);
                            }
                        })
/*                        new KeyFrame(Duration.millis(700), new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent t) {
                                dog.getChildren().setAll(dog7);
                            }
                        })*/
/*                        new KeyFrame(Duration.millis(800), new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent t) {
                                dog.getChildren().setAll(dog8);
                            }
                        }),
                        new KeyFrame(Duration.millis(900), new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent t) {
                                dog.getChildren().setAll(dog9);
                            }
                        })*/
                )
                .build().play();
        final Group root = new Group(dog);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Test");
        primaryStage.setScene(scene);
        primaryStage.show();
        startWalking();
    }

    private void startWalking() {
        walk = TranslateTransitionBuilder.create()
                .node(dog)
                .fromX(-200)
                .toX(800)
                .duration(Duration.seconds(5))
                .onFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        startWalking();
                    }
                }).build();
        walk.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
