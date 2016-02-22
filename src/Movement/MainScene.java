package Movement;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

/**
 * Movement.Copter
 * Created by Dan on 2/12/2016.
 */
public class MainScene extends Application {

    private int playerSpeed;
    private ImageView player;
    private Image copterSrc;
    private Rectangle barrier;
    private int nBarriers;
    private Barrier[] barriers;
    private double barrierLocation;
    private Group layout;
    private Scene scene;
    private double playerX;
    private double playerY;
    private Random rand;

    public MainScene() {
        rand = new Random();
        player = new ImageView();
        copterSrc = new Image(getClass().getResourceAsStream("../images/copter.png"));
        barrier = new Rectangle(20, 200);
        layout = new Group();
        scene = new Scene(layout, 800, 300);
        barrierLocation = scene.getWidth();
        nBarriers = 10;
        barriers = new Barrier[nBarriers];
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        playerX = 10;
        playerY = (scene.getHeight() / 2) - (copterSrc.getHeight());
        playerSpeed = 10;

        player.setImage(copterSrc);
        player.setFitWidth(50);
        player.setLayoutX(playerX);
        player.setLayoutY(playerY);
        player.setPreserveRatio(true);
        player.setSmooth(true);
        player.setCache(true);

        barrier.setLayoutX(barrierLocation);
        barrier.setLayoutY(100);
        barrier.setFill(Color.LIME);

        for (int i = 0; i < barriers.length; i++) {
            barriers[i] = new Barrier(200, i + 100, 20, 200);
            barriers[i].setSpeed(10);
            barriers[i].setColor(Color.LIME);
            layout.getChildren().add(barriers[i]);
            System.out.println(barriers[i].toString());
        }

        System.out.println(layout.getChildren().toString());

        scene.setFill(Color.BLACK);
        layout.getChildren().addAll(player, barrier);
        primaryStage.setTitle("Copter");
        primaryStage.getIcons().add(copterSrc);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    playerMove(primaryStage);
                    barrier.setLayoutX(barrierLocation -= 10);
//                    System.out.println(barrierLocation);
                    if (barrierLocation < 0) {
                        barrierLocation = scene.getWidth();
                    }
                    if (barrier.isHover()) {
                        barrier.setFill(Color.CYAN);
                    } else {
                        barrier.setFill(Color.LIME);
                    }
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
                if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) {
                    playerY -= playerSpeed;
                    player.setLayoutY(playerY);
                    player.setRotate(0);
                }
                if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
                    playerY += playerSpeed;
                    player.setLayoutY(playerY);
                    player.setRotate(0);

                }
                if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
                    playerX -= playerSpeed;
                    player.setLayoutX(playerX);
                    player.setRotate(340);
                    player.setScaleX(-1);
                }
                if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
                    playerX += playerSpeed;
                    player.setLayoutX(playerX);
                    player.setRotate(20);
                    player.setScaleX(1);
                }

                // TODO check bounds logic
                if (player.getLayoutY() < 0) {
                    playerY = scene.getHeight();
                }
                if (player.getLayoutY() > scene.getHeight()) {
                    playerY = 0;
                }
                if (player.getLayoutX() < 0) {
                    playerX = scene.getWidth();
                }
                if (player.getLayoutX() > scene.getWidth()) {
                    playerX = 0;
                }
//                System.out.println("X: " + player.getLayoutX() + " Y: " + player.getLayoutY());
            }
        });
    }
}
