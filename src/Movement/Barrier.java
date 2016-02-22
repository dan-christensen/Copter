package Movement;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Movement.Copter
 * Created by Dan on 2/16/2016.
 */
public class Barrier extends Rectangle {

    private int speed;
    private Color color;

    public Barrier() {
    }

    public Barrier(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getLocation() {
        return "x: " + super.getX() + " y: " + super.getY();
    }
}