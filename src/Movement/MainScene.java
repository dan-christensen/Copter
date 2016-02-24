package Movement;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
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
    private ImageView player;
    private Image copterSrc;
    private int nBarriers;
    private List<Barrier> barriers;
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


        playerX = 75;
        playerY = (scene.getHeight() / 2) - (copterSrc.getHeight());
        playerSpeed = 10;

        player.setImage(copterSrc);
        player.setFitWidth(50);
        player.setLayoutX(playerX);
        player.setLayoutY(playerY);
        player.setPreserveRatio(true);
        player.setSmooth(true);
        player.setCache(true);

        for (int i = 0; i < nBarriers; i++) {
            barriers.add(new Barrier());
            double x = 0;
            if (i == 0) {
                x = scene.getWidth();
            }
            if (i > 0) {
                x = barriers.get(i - 1).getLayoutX() + 500;
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
        layout.getChildren().addAll(player);
        primaryStage.setTitle("Copter");
        primaryStage.getIcons().add(copterSrc);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        new AnimationTimer() {
            @Override
            public void handle(long now) {
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



/*                if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
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
                }*/

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
