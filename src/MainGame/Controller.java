package MainGame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
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

    private Group gameLayout;
    private StackPane menuLayout;
    private Scene sceneGame;
    private Scene mainMenu;
    private Player player;
    private int playerSpeed;

    private List<Barrier> barriers;
    Barrier topRect;
    Barrier botRect;
    private List<Barrier> trail;


    public Controller() {
        rand = new Random();
        gameLayout = new Group();
        menuLayout = new StackPane();
        sceneGame = new Scene(gameLayout, 800, 300, Color.BLACK);
        mainMenu = new Scene(menuLayout, 300, 300, Color.BLACK);
    }

    public void start(Stage primaryStage) throws Exception {
        Button start = new Button("Play");
        menuLayout.getChildren().add(start);
        start.setOnAction(e -> {
            primaryStage.setScene(sceneGame);
            gameStart();
        });

        primaryStage.setTitle("Copter");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../images/copter.png")));
        primaryStage.setScene(mainMenu);
        primaryStage.setResizable(false);
        primaryStage.show();
        tick(primaryStage);
    }

    private void gameStart() {
        player = new Player(new Image(getClass().getResourceAsStream("../images/copter.png")));
        double playerX = 50;
        double playerY = 100;
        barriers = new ArrayList<>();
        int nBarriers = 100;
        int barrierGap = 300;
        topRect = new Barrier();
        botRect = new Barrier();
        playerSpeed = 5;
        trail = new ArrayList<>();


        player.setBounds(playerX, playerY, 50, 50);
        player.setRotation(20);
        player.getSrc().setPreserveRatio(true);
        player.getSrc().setSmooth(true);
        player.getSrc().setCache(true);
        gameLayout.getChildren().add(player.getSrc());


        for (int i = 0; i < nBarriers; i++) {
            barriers.add(new Barrier());
            double x = 0;
            if (i == 0) {
                x = sceneGame.getWidth();
            }
            if (i > 0) {
                x = barriers.get(i - 1).getX() + barrierGap;
            }

            double y = rand.nextInt(((int) sceneGame.getHeight() - 100) - 10) + 10;

            barriers.get(i).setBounds(x, y, 20, rand.nextInt(100 - 50) + 50);
            barriers.get(i).setFill(Color.LIME);
            barriers.get(i).setSpeed(-10);
            gameLayout.getChildren().add(barriers.get(i).getSrc());

        }

        topRect.setBounds(0, 0, (int) sceneGame.getWidth() + 10, 10);
        topRect.setFill(Color.LIME);

        botRect.setBounds(0, 290, (int) sceneGame.getWidth() + 10, 10);
        botRect.setFill(Color.LIME);
        gameLayout.getChildren().addAll(topRect.getSrc(), botRect.getSrc());
    }

    private void gameStop() {
        gameLayout.getChildren().removeAll(player.getSrc(), topRect.getSrc(), botRect.getSrc());
        for (Barrier barrierList : barriers) {
            gameLayout.getChildren().remove(barrierList.getSrc());
        }
        for (Barrier trails : trail) {
            gameLayout.getChildren().remove(trails);
        }
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
        trail.get(i).setBounds(player.getX(), player.getY() + 5, 10, 3);
        trail.get(i).setFill(Color.WHITESMOKE);
        trail.get(i).setSpeed(-10);
        gameLayout.getChildren().add(trail.get(i).getSrc());
        i++;

        for (Barrier trails : trail) {
            if (trails.getY() < 0) {
                gameLayout.getChildren().remove(trails.getSrc());
                trails.setSpeed(0);
            }
            trails.moveX(trails.getSpeed());
        }

        player.moveY(player.getSpeed());
        for (Barrier barrierList : barriers) {

            // Collision detection
            if (player.getSrc().getBoundsInParent().intersects(barrierList.getSrc().getBoundsInParent()) || player.getSrc().getBoundsInParent().intersects(topRect.getSrc().getBoundsInParent()) || player.getSrc().getBoundsInParent().intersects(botRect.getSrc().getBoundsInParent())) {
                gameStop();
                primaryStage.setScene(mainMenu);
            }
            if (barrierList.getX() + barrierList.getWidth() < 0) {
                gameLayout.getChildren().remove(barrierList.getSrc());
                barrierList.setSpeed(0);
            }
        }
    }

    private void playerMove() {
        sceneGame.setOnKeyPressed(new EventHandler<KeyEvent>() {
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
        sceneGame.setOnKeyReleased(new EventHandler<KeyEvent>() {
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