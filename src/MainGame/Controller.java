package MainGame;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
    private Actor player;
    private int playerSpeed;

    Stage primaryStage;
    private List<Actor> barriers;
    private int nBarriers;
    private int barrierGap;
    private int barrierSpeed;
    Actor topRect;
    Actor botRect;
    private List<Actor> trail;
    private boolean noclip;

    int tempScore = 0;
    private int score = 0;

    GameLoop tick = new GameLoop();

    public Controller() {
        noclip = false;
        rand = new Random();
        gameLayout = new Group();
        menuItems = new VBox(20);
        sceneGame = new Scene(gameLayout, 800, 300, Color.BLACK);
        mainMenu = new Scene(menuItems, 300, 300, Color.BLACK);
        barrierSpeed = -10;
        nBarriers = 200;
        barrierGap = 300;
    }

    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Button start = new Button("Start");
        Button quit = new Button("Exit");
        menuItems.getChildren().addAll(start, quit);
        menuItems.setStyle("-fx-background-color: black");
        menuItems.setAlignment(Pos.CENTER);
        start.setOnAction(e -> {
            this.primaryStage.setScene(sceneGame);
            gameStart();
        });
        quit.setOnAction(e -> this.primaryStage.close());

        this.primaryStage.setTitle("Copter");
        this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../images/copter.png")));
        this.primaryStage.setScene(mainMenu);
        this.primaryStage.setResizable(false);
        this.primaryStage.show();
    }

    private void gameStart() {
        player = new Actor();
        double playerX = 50;
        double playerY = 100;
        barriers = new ArrayList<>();
        topRect = new Actor();
        botRect = new Actor();
        playerSpeed = 5;
        trail = new ArrayList<>();

        topRect.setSrc(new Rectangle());
        topRect.setBounds(0, 0, (int) sceneGame.getWidth() + 10, 10);
        topRect.setFill(Color.LIME);

        botRect.setSrc(new Rectangle());
        botRect.setBounds(0, 290, (int) sceneGame.getWidth() + 10, 10);
        botRect.setFill(Color.LIME);
        gameLayout.getChildren().addAll(topRect.getSrc(), botRect.getSrc());

        player.setSrc(new Rectangle());
        player.setBounds(playerX, playerY, 50, 50);
        player.setImageSrc(new Image(getClass().getResourceAsStream("../images/copter.png")));
        player.setImageLocation(player.getX(), player.getY());
        player.setImageWidth(50);
        player.setImageHeight(50);
        player.setSpeed(playerSpeed);
        player.setFill(Color.CYAN);
        player.setRotate(20);
        gameLayout.getChildren().addAll(/*player.getSrc(),*/ player.getImageSrc());

        for (int i = 0; i < nBarriers; i++) {
            barriers.add(new Actor());
            barriers.get(i).setSrc(new Rectangle());
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
        tick.start();
    }

    private void gameStop() throws IOException {
        File highScore = new File("src\\res\\high_score.txt");
        FileWriter writer = new FileWriter(highScore, true);
        Scanner hsIn = new Scanner(new FileReader(highScore));
//        int hs = hsIn.nextInt();
        tick.stop();
        gameLayout.getChildren().removeAll(player.getImageSrc(), topRect.getSrc(), botRect.getSrc());
        for (Actor barrierList : barriers) {
            gameLayout.getChildren().remove(barrierList.getSrc());
        }
        for (Actor trails : trail) {
            gameLayout.getChildren().remove(trails.getSrc());
        }
        if (score > hsIn.nextInt()) {
            FileWriter temp = new FileWriter(highScore, false);
            temp.write("");
            temp.close();
            writer.write("" + score);
            writer.flush();
            writer.close();
        }
//        System.out.println(hs);
        score = 0;
        this.primaryStage.setScene(mainMenu);
    }

    private void handleInput() {
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
                if (event.getCode().equals(KeyCode.Q)) {
                    try {
                        gameStop();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (event.getCode().equals(KeyCode.N)) {
                    noclip = !noclip;
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

    private void moveBarriers() {
        for (Actor barrierList : barriers) {
            barrierList.moveX(barrierList.getSpeed());
        }
    }

    private void score() {
        this.primaryStage.setTitle("Copter | " + score);
        if (tempScore % 5 == 0) {
            score++;
        }
        tempScore++;

    }

    private class GameLoop extends AnimationTimer {
        @Override
        public void handle(long now) {
            int i = 0;
            trail.add(i, new Actor());
            trail.get(i).setSrc(new Rectangle());
            trail.get(i).setBounds(
                    player.getImageX(),
                    player.getImageY() + (rand.nextInt(10 - 5) + 5),
                    3,
                    3);
            trail.get(i).setFill(Color.WHITESMOKE);
            trail.get(i).setXSpeed(rand.nextInt((-1) - (-5)) + (-5));
            trail.get(i).setYSpeed(rand.nextInt(1 - (-1)) + (-1));
            gameLayout.getChildren().add(trail.get(i).getSrc());

            for (Actor trails : trail) {
                if (trails.getX() < 0) {
                    gameLayout.getChildren().remove(trails.getSrc());
                    trails.setSpeed(0);
                } else {
                    trails.move(
                            trails.getXSpeed(),
                            trails.getYSpeed());
                }
            }

            checkCollision();
            player.moveY(player.getSpeed());
            handleInput();
            moveBarriers();
            score();
        }

        private void checkCollision() {
            if (!noclip) {
                for (Actor barrierList : barriers) {

                    if (player.getImageSrc().getBoundsInParent().intersects(barrierList.getSrc().getBoundsInParent())) {
                        playerCrash();
                    }
                    if (barrierList.getX() + barrierList.getWidth() < 0) {
                        gameLayout.getChildren().remove(barrierList.getSrc());
                        barrierList.setSpeed(0);
                    }
                }

                if (player.getImageSrc().getBoundsInParent().intersects(topRect.getSrc().getBoundsInParent()) || player.getImageSrc().getBoundsInParent().intersects(botRect.getSrc().getBoundsInParent())) {
                    playerCrash();

                }
            }
        }

        private void playerCrash() {
            tick.stop();
            Circle explosion = new Circle(
                    player.getX() + (player.getImageWidth()) / 2,
                    player.getY() + (player.getImageHeight() / 2),
                    10,
                    Color.ORANGERED);
            gameLayout.getChildren().add(explosion);
            Timeline tl = new Timeline(250);
            KeyValue kv1 = new KeyValue(explosion.radiusProperty(), 60);
            KeyValue kv2 = new KeyValue(explosion.fillProperty(), Color.BLACK);
            KeyFrame kf1 = new KeyFrame(Duration.millis(100), kv1);
            KeyFrame kf2 = new KeyFrame(Duration.millis(250), kv2);
            tl.getKeyFrames().addAll(kf1, kf2);
            tl.play();
            tl.setOnFinished(e -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                    ignored.printStackTrace();
                }
                try {
                    gameStop();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        }
    }
}