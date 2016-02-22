package Movement;

import javafx.scene.shape.Rectangle;

/**
 * Movement.Copter
 * Created by Dan on 2/16/2016.
 */
public class Barrier extends Rectangle {

    private int speed;

    public Barrier() {
    }

    public Barrier(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}