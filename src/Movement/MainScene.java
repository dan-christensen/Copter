package Movement;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Movement.Copter
 * Created by Dan on 2/12/2016.
 */
public class MainScene extends Application {

    private int playerSpeed;
    private Player player;
    private Image copterSrc;
    private int nBarriers;
    private List<Barrier> barriers;
    private double barrierLocation;
    private Group layout;
    private Scene scene;
    private double playerX;
    private double playerY;
    private Random rand;
    private Boolean isFalling = true;

    public MainScene() {
        rand = new Random();
        player = new Player();
        copterSrc = new Image(getClass().getResourceAsStream("../images/copter.png"));
        layout = new Group();
        scene = new Scene(layout, 800, 300);
        barrierLocation = scene.getWidth();
        nBarriers = 100;
        barriers = new ArrayList<>();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        playerX = 50;
        playerY = (scene.getHeight() / 2) - ((copterSrc.getHeight() / 2));
        playerSpeed = 10;

        player.getSrc().setImage(copterSrc);
        player.getSrc().setFitWidth(50);
        player.getSrc().setLayoutX(playerX);
        player.getSrc().setLayoutY(playerY);
        player.getSrc().setPreserveRatio(true);
        player.getSrc().setSmooth(true);
        player.getSrc().setCache(true);

        for (int i = 0; i < nBarriers; i++) {
            barriers.add(new Barrier());
            double x = 0;
            if (i == 0) {
                x = scene.getWidth();
            }
            if (i > 0) {
                x = barriers.get(i - 1).getLayoutX() + 600;
            }
//            barriers.get(i).setLayoutX(rand.nextInt((int) scene.getWidth()));
            barriers.get(i).setLayoutX(x);
            barriers.get(i).setLayoutY(rand.nextInt((int) scene.getHeight()));
            barriers.get(i).setWidth(20);
            barriers.get(i).setHeight(rand.nextInt(100 - 50) + 50);
            barriers.get(i).setFill(Color.LIME);
            barriers.get(i).setSpeed(10);
            layout.getChildren().add(barriers.get(i));
        }

        scene.setFill(Color.BLACK);
        layout.getChildren().addAll(player.getSrc());
        primaryStage.setTitle("Copter");
        primaryStage.getIcons().add(copterSrc);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (isFalling) {
                    player.getSrc().setRotate(20);
                    player.getSrc().setLayoutY(playerY += 5);
                }
                for (Barrier barrierList : barriers) {
                    double location = barrierList.getLayoutX();
//                    if (player.getBoundsInParent().intersects(barrierList.getBoundsInParent())) {
//                        primaryStage.close();
//                    }
//                    if (barrierList.getLayoutX() < 0) {
//                        location = scene.getWidth();
//                    }
                    barrierList.setLayoutX(location -= barrierList.getSpeed());
                }
                try {
                    playerMove(primaryStage);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void playerMove(Stage primaryStage) throws InterruptedException {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE) {
                    isFalling = false;
                    player.getSrc().setRotate(340);
                    Timeline tl = new Timeline(60);
                    KeyValue ke1 = new KeyValue(player.getSrc().layoutYProperty(), player.getSrc().getLayoutY() - 100);
                    KeyFrame kf1 = new KeyFrame(Duration.millis(250), ke1);
//                    tl.setCycleCount(2);
//                    tl.setAutoReverse(true);
                    tl.getKeyFrames().add(kf1);
                    tl.play();
                    playerY = player.getSrc().getLayoutY();
                    tl.setOnFinished(e -> isFalling = true);
                }
                if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) {
                    playerY -= playerSpeed;
                    player.getSrc().setLayoutY(playerY);
                    player.getSrc().setRotate(0);
                }
                if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
                    playerY += playerSpeed;
                    player.getSrc().setLayoutY(playerY);
                    player.getSrc().setRotate(0);

                }
                if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
                    playerX -= playerSpeed;
                    player.getSrc().setLayoutX(playerX);
                    player.getSrc().setRotate(340);
                    player.getSrc().setScaleX(-1);
                }
                if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
                    playerX += playerSpeed;
                    player.getSrc().setLayoutX(playerX);
                    player.getSrc().setRotate(20);
                    player.getSrc().setScaleX(1);
                }

                // TODO check bounds logic
                if (player.getSrc().getLayoutY() < 0) {
                    playerY = scene.getHeight();
                }
                if (player.getSrc().getLayoutY() > scene.getHeight()) {
                    playerY = 0;
                }
                if (player.getSrc().getLayoutX() < 0) {
                    playerX = scene.getWidth();
                }
                if (player.getSrc().getLayoutX() > scene.getWidth()) {
                    playerX = 0;
                }
//                System.out.println("X: " + player.getLayoutX() + " Y: " + player.getLayoutY());
            }
        });
    }
}
