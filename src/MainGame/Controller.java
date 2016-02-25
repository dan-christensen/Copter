package MainGame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
    private double playerX;
    private double playerY;

    private Barrier barrier;
    private List<Barrier> barriers;
    private int nBarriers;

    public Controller() {
        rand = new Random();
        layout = new Group();
        scene = new Scene(layout, 800, 300, Color.BLACK);
        player = new Player(new Image(getClass().getResourceAsStream("../images/copter.png")));
        playerX = 10;
        playerY = 10;
        barrier = new Barrier();
        barriers = new ArrayList<>();
        nBarriers = 100;
    }

    public void start(Stage primaryStage) throws Exception {
        player.setBounds(playerX, playerY, 50, 50);
        player.setSpeed(10);
        player.getSrc().setPreserveRatio(true);
        player.getSrc().setSmooth(true);
        player.getSrc().setCache(true);
        layout.getChildren().add(player.getSrc());

        barrier.setBounds(500, 100, 20, 200);
        barrier.setFill(Color.LIME);
        layout.getChildren().add(barrier.getSrc());

        for (int i = 0; i < nBarriers; i++) {
            barriers.add(new Barrier());
            double x = 0;
            if (i == 0) {
                x = scene.getWidth();
            }
            if (i > 0) {
                x = barriers.get(i - 1).getX() + 600;
            }
//            barriers.get(i).setLayoutX(rand.nextInt((int) scene.getWidth()));
            barriers.get(i).setX(x);
            barriers.get(i).setY(rand.nextInt((int) scene.getHeight()));
            barriers.get(i).setWidth(20);
            barriers.get(i).setHeight(rand.nextInt(100 - 50) + 50);

            barriers.get(i).setBounds(x, rand.nextInt((int) scene.getHeight()), 20, rand.nextInt(100 - 50) + 50);

            barriers.get(i).setFill(Color.LIME);
            barriers.get(i).setSpeed(-10);
            layout.getChildren().add(barriers.get(i).getSrc());

        }

        primaryStage.setTitle("Copter");
        primaryStage.getIcons().add(player.getSrc().getImage());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        tick();
    }

    public void tick() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                barrier.moveX(1);
                for (Barrier barrierList : barriers) {
                    barrierList.moveX(barrierList.getSpeed());
                }
            }
        }.start();
    }
}
