package Testing.MainGameTest;

import javafx.scene.image.ImageView;

/**
 * Testing.MainGameTest.Copter
 * Created by Dan on 2/24/2016.
 */
public class Player2 {
    private ImageView src = new ImageView();
    private double x;
    private double y;
    private double width;
    private double height;
    private int speed;

    public Player2() {
    }

    public Player2(ImageView src, double width, double height) {
        this.src = src;
        this.width = width;
        this.height = height;
    }

    public Player2(ImageView src, double x, double y, double width, double height) {
        this.src = src;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public ImageView getSrc() {
        return src;
    }

    public void setSrc(ImageView src) {
        this.src = src;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
