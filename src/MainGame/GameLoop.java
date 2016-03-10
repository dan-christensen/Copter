package MainGame;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;

/**
 * MainGame.Copter
 * Created by Dan on 3/10/2016.
 */
class GameLoop extends AnimationTimer {
    private Controller controller;

    public GameLoop(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void handle(long now) {
        int i = 0;
        controller.trail.add(i, new Actor());
        controller.trail.get(i).setSrc(new Rectangle());
        controller.trail.get(i).setBounds(
                controller.player.getImageX(),
                controller.player.getImageY() + (controller.rand.nextInt(10 - 5) + 5),
                3,
                3);
        controller.trail.get(i).setFill(Color.WHITESMOKE);
        controller.trail.get(i).setXSpeed(controller.rand.nextInt((-1) - (-5)) + (-5));
        controller.trail.get(i).setYSpeed(controller.rand.nextInt(1 - (-1)) + (-1));
        controller.gameLayout.getChildren().add(controller.trail.get(i).getSrc());

        for (Actor trails : controller.trail) {
            if (trails.getX() < 0) {
                controller.gameLayout.getChildren().remove(trails.getSrc());
                trails.setSpeed(0);
            } else {
                trails.move(
                        trails.getXSpeed(),
                        trails.getYSpeed());
            }
        }

        checkCollision();
        controller.player.moveY(controller.player.getSpeed());
        controller.handleInput();
        controller.moveBarriers();
        controller.score();
    }

    private void checkCollision() {
        if (!controller.noclip) {
            for (Actor barrierList : controller.barriers) {

                if (controller.player.getSrc().getBoundsInParent().intersects(barrierList.getSrc().getBoundsInParent())) {
                    playerCrash();
                }
                if (barrierList.getX() + barrierList.getWidth() < 0) {
                    controller.gameLayout.getChildren().remove(barrierList.getSrc());
                    barrierList.setSpeed(0);
                }
            }

            if (controller.player.getSrc().getBoundsInParent().intersects(controller.topRect.getSrc().getBoundsInParent()) || controller.player.getSrc().getBoundsInParent().intersects(controller.botRect.getSrc().getBoundsInParent())) {
                playerCrash();

            }
        }
    }

    private void playerCrash() {
        controller.tick.stop();
        Circle explosion = new Circle(
                controller.player.getX() + (controller.player.getImageWidth()) / 2,
                controller.player.getY() + (controller.player.getImageHeight() / 2),
                10,
                Color.ORANGERED);
        controller.gameLayout.getChildren().add(explosion);
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
                controller.gameStop();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }
}
