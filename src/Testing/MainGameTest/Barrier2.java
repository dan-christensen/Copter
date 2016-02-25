package Testing.MainGameTest;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Testing.MainGameTest.Copter
 * Created by Dan on 2/16/2016.
 */
public class Barrier2 extends Rectangle {

    private int speed;
    private Color color;

    public Barrier2() {
    }

    public Barrier2(double x, double y, double width, double height) {
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