package MainGame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
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
    private VBox menuItems;
    private Scene sceneGame;
    private Scene mainMenu;
    private Player player;
    private int playerSpeed;

    private List<Barrier> barriers;
    private int nBarriers;
    private int barrierGap;
    private int barrierSpeed;
    Barrier topRect;
    Barrier botRect;
    private List<Barrier> trail;
    private boolean noclip;

    private int score = 0;


    public Controller() {
        rand = new Random();
        gameLayout = new Group();
        menuItems = new VBox(20);
        sceneGame = new Scene(gameLayout, 800, 300, Color.BLACK);
        mainMenu = new Scene(menuItems, 300, 300, Color.BLACK);
        noclip = true;
        barrierSpeed = -10;
        nBarriers = 100;
        barrierGap = 300;
    }

    public void start(Stage primaryStage) throws Exception {
        Button start = new Button("Start");
        Button quit = new Button("Exit");
        menuItems.getChildren().addAll(start, quit);
        menuItems.setStyle("-fx-background-color: black");
        menuItems.setAlignment(Pos.CENTER);
        start.setOnAction(e -> {
            primaryStage.setScene(sceneGame);
            gameStart(primaryStage);
        });
        quit.setOnAction(e -> primaryStage.close());

        primaryStage.setTitle("Copter");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../images/copter.png")));
        primaryStage.setScene(mainMenu);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void gameStart(Stage primaryStage) {
        player = new Player(new Image(getClass().getResourceAsStream("../images/copter.png")));
        double playerX = 50;
        double playerY = 100;
        barriers = new ArrayList<>();
        topRect = new Barrier();
        botRect = new Barrier();
        playerSpeed = 5;
        trail = new ArrayList<>();


        player.setBounds(playerX, playerY, 50, 50);
        player.setRotate(20);
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
            barriers.get(i).setSpeed(barrierSpeed);
            gameLayout.getChildren().add(barriers.get(i).getSrc());

        }

        topRect.setBounds(0, 0, (int) sceneGame.getWidth() + 10, 10);
        topRect.setFill(Color.LIME);

        botRect.setBounds(0, 290, (int) sceneGame.getWidth() + 10, 10);
        botRect.setFill(Color.LIME);
        gameLayout.getChildren().addAll(topRect.getSrc(), botRect.getSrc());
        tick(primaryStage);
    }

    private void gameStop() {
        gameLayout.getChildren().removeAll(player.getSrc(), topRect.getSrc(), botRect.getSrc());
        for (Barrier barrierList : barriers) {
            gameLayout.getChildren().remove(barrierList.getSrc());
        }
        for (Barrier trails : trail) {
            gameLayout.getChildren().remove(trails.getSrc());
        }
    }

    public void tick(Stage primaryStage) {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
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

                if (!noclip) {
                    for (Barrier barrierList : barriers) {

                        if (player.getSrc().getBoundsInParent().intersects(barrierList.getSrc().getBoundsInParent())) {
                            playerCrash();
                        }
                        if (barrierList.getX() + barrierList.getWidth() < 0) {
                            gameLayout.getChildren().remove(barrierList.getSrc());
                            barrierList.setSpeed(0);
                        }
                    }

                    if (player.getSrc().getBoundsInParent().intersects(topRect.getSrc().getBoundsInParent()) || player.getSrc().getBoundsInParent().intersects(botRect.getSrc().getBoundsInParent())) {
                        playerCrash();

                    }
                }

                if (score > 500) {
                    barrierSpeed *= 2;
                }

                playerMove();
                barrierMove();
                score(primaryStage);
            }

            private void playerCrash() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stop();
                gameStop();
                primaryStage.setScene(mainMenu);
            }
        }.start();
    }

    private void playerMove() {
        sceneGame.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.DOWN) || event.getCode().equals(KeyCode.S)) {
                    player.setSpeed(playerSpeed);
                    player.setRotate(30);
                    System.out.println(player.getY());
                }
                if (event.getCode().equals(KeyCode.UP) || event.getCode().equals(KeyCode.W)) {
                    player.setSpeed(playerSpeed * (-1));
                    player.setRotate(10);
                    System.out.println(player.getY());
                }
                if (event.getCode() == KeyCode.SPACE) {
                    player.setRotate(10);
                    player.setSpeed(-5);
                }
            }
        });
        sceneGame.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                player.setSpeed(0);
                player.setRotate(20);
                if (event.getCode().equals(KeyCode.SPACE)) {
                    player.setSpeed(5);
                }
            }
        });
    }

    private void barrierMove() {
        for (Barrier barrierList : barriers) {
            barrierList.moveX(barrierList.getSpeed());
        }
    }

    private void score(Stage primaryStage) {
        score++;
        primaryStage.setTitle("Copter | " + score);
        System.out.println(score);

    }
}