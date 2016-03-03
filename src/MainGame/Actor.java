package MainGame;

import javafx.beans.property.IntegerProperty;

/**
 * Testing.Copter
 * Created by Dan on 2/24/2016.
 */
public abstract class Actor {
    private double x;
    private double y;
    private int width;
    private int height;
    private int speed;
    private double tempX;
    private double tempY;

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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setBounds(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(int xSpeed, int ySpeed) {
        this.setX(tempX += xSpeed);
        this.setY(tempY += ySpeed);
    }

    public void moveX(int xSpeed) {
        this.setX(tempX += xSpeed);
    }

    public void moveY(int ySpeed) {
        this.setY(tempY += ySpeed);
    }
}