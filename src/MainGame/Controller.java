package MainGame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * MainGame.Copter
 * Created by Dan on 2/24/2016.
 */

public class Controller extends Application {

    Random rand;

    private Group layout;
    private Scene scene;
    private Player player;
    private int playerSpeed;
    private double playerX;
    private double playerY;

    private List<Barrier> barriers;
    private int nBarriers;
    private int barrierGap;
    private List<Barrier> trail;


    public Controller() {
        rand = new Random();
        layout = new Group();
        scene = new Scene(layout, 800, 300, Color.BLACK);
        player = new Player(new Image(getClass().getResourceAsStream("../images/copter.png")));
        playerX = 50;
        playerY = 100;
        barriers = new ArrayList<>();
        nBarriers = 100;
        barrierGap = 300;
        playerSpeed = 5;
        trail = new ArrayList<>();
    }

    public void start(Stage primaryStage) throws Exception {
        player.setBounds(playerX, playerY, 50, 50);
        player.setRotation(20);
        player.getSrc().setPreserveRatio(true);
        player.getSrc().setSmooth(true);
        player.getSrc().setCache(true);
        layout.getChildren().add(player.getSrc());


        for (int i = 0; i < nBarriers; i++) {
            barriers.add(new Barrier());
            double x = 0;
            if (i == 0) {
                x = scene.getWidth();
            }
            if (i > 0) {
                x = barriers.get(i - 1).getX() + barrierGap;
            }

            double y = rand.nextInt(((int) scene.getHeight() - 100) - 10) + 10;

            barriers.get(i).setBounds(x, y, 20, rand.nextInt(100 - 50) + 50);
            barriers.get(i).setFill(Color.LIME);
            barriers.get(i).setSpeed(-10);
            layout.getChildren().add(barriers.get(i).getSrc());

        }

        primaryStage.setTitle("Copter");
        primaryStage.getIcons().add(player.getSrc().getImage());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        System.out.println(player.getY());
        tick(primaryStage);
    }

    public void tick(Stage primaryStage) {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update(primaryStage);
                playerMove();
                barrierMove();
            }
        }.start();
    }

    private void update(Stage primaryStage) {

        int i = 0;
        trail.add(i, new Barrier());
        trail.get(i).setBounds(player.getX(), player.getY() + 5, 5, 3);
        trail.get(i).setFill(Color.CYAN);
        trail.get(i).setSpeed(-5);
        layout.getChildren().add(trail.get(i).getSrc());
        i++;

        for (Barrier trails : trail) {
            trails.moveX(trails.getSpeed());
        }

        player.moveY(player.getSpeed());
        for (Barrier barrierList : barriers) {
            if (player.getSrc().getBoundsInParent().intersects(barrierList.getSrc().getBoundsInParent())) {
                primaryStage.close();
            }
            if (barrierList.getX() + barrierList.getWidth() < 0) {
                barrierList.setSpeed(0);
            }
        }
    }

    private void playerMove() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.DOWN) || event.getCode().equals(KeyCode.S)) {
                    player.setSpeed(playerSpeed);
                    player.setRotation(10);
                    System.out.println(player.getY());
                }
                if (event.getCode().equals(KeyCode.UP) || event.getCode().equals(KeyCode.W)) {
                    player.setSpeed(playerSpeed * (-1));
                    player.setRotation(30);
                    System.out.println(player.getY());
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                player.setSpeed(0);
                player.setRotation(20);
            }
        });
    }

    private void barrierMove() {
        for (Barrier barrierList : barriers) {
            barrierList.moveX(barrierList.getSpeed());
        }
    }
}