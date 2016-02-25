package MainGame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * MainGame.Copter
 * Created by Dan on 2/25/2016.
 */
public class Barrier extends Actor {
    private Rectangle src;
    private double tempX;
    private double tempY;

    public Barrier() {
        src = new Rectangle();
    }

    public Rectangle getSrc() {
        return src;
    }

    public void setSrc(Rectangle src) {
        this.src = src;
    }

    @Override
    public double getX() {
        return this.getSrc().getLayoutX();
    }

    @Override
    public void setX(double x) {
        this.getSrc().setLayoutX(x);
        tempX = this.getX();
    }

    @Override
    public double getY() {
        return this.getSrc().getLayoutY();
    }

    @Override
    public void setY(double y) {
        this.getSrc().setLayoutY(y);
        tempY = this.getY();
    }

    @Override
    public int getWidth() {
        return (int) this.getSrc().getWidth();
    }

    @Override
    public void setWidth(int width) {
        this.getSrc().setWidth(width);
    }

    @Override
    public int getHeight() {
        return (int) this.getSrc().getHeight();
    }

    @Override
    public void setHeight(int height) {
        this.getSrc().setHeight(height);
    }

    @Override
    public void setBounds(double x, double y, int width, int height) {
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
    }

    @Override
    public void move(int xSpeed, int ySpeed) {
        this.setX(tempX += xSpeed);
        this.setY(tempY += ySpeed);
    }

    @Override
    public void moveX(int xSpeed) {
        this.setX(tempX += xSpeed);
    }

    @Override
    public void moveY(int ySpeed) {
        this.setY(tempY += ySpeed);
    }

    public void setFill(Color color) {
        this.getSrc().setFill(color);
    }
}
