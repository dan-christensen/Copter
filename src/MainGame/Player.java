package MainGame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Testing.PopUp.Copter
 * Created by Dan on 2/24/2016.
 */
public class Player extends Actor {
    private ImageView src;
    private double tempX;
    private double tempY;

    public Player() {
        src = new ImageView();
    }

    public Player(Image src) {
        this.src = new ImageView();
        this.getSrc().setImage(src);
    }

    public ImageView getSrc() {
        return src;
    }

    public void setSrc(Image src) {
        this.getSrc().setImage(src);
    }

    @Override
    public double getX() {
        return this.getSrc().getLayoutX();
    }

    @Override
    public void setX(double x) {
        this.getSrc().setLayoutX(x);
        tempX = x;
    }

    @Override
    public double getY() {
        return this.getSrc().getLayoutY();
    }

    @Override
    public void setY(double y) {
        this.getSrc().setLayoutY(y);
        tempY = y;
    }

    @Override
    public int getWidth() {
        return (int) this.getSrc().getFitWidth();
    }

    @Override
    public void setWidth(int width) {
        this.getSrc().setFitWidth(width);
    }

    @Override
    public void setHeight(int height) {
        this.getSrc().setFitHeight(height);
    }

    @Override
    public int getHeight() {
        return (int) this.getSrc().getFitHeight();
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

    public void setRotation(double degree) {
        this.getSrc().setRotate(degree);
    }
}