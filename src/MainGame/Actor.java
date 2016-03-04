package MainGame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Testing.Copter
 * Created by Dan on 2/24/2016.
 */
public class Actor {
    private Rectangle src;
    private double x;
    private double y;
    private double width;
    private double height;
    private int speed;
    private double tempX;
    private ImageView imageSrc;

    private double tempY;

    public Rectangle getSrc() {
        return src;
    }

    public void setSrc(Rectangle src) {
        this.src = src;
    }

    public double getX() {
        return this.getSrc().getLayoutX();
    }

    public void setX(double x) {
        this.getSrc().setLayoutX(x);
        tempX = x;
    }

    public double getY() {
        return this.getSrc().getLayoutY();
    }

    public void setY(double y) {
        this.getSrc().setLayoutY(y);
        tempY = y;
    }

    public double getWidth() {
        return this.getSrc().getWidth();
    }

    public void setWidth(double width) {
        this.getSrc().setWidth(width);
    }

    public double getHeight() {
        return this.getSrc().getHeight();
    }

    public void setHeight(double height) {
        this.getSrc().setHeight(height);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setFill(Color color) {
        this.getSrc().setFill(color);
    }

    public void setBounds(double x, double y, double width, double height) {
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
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

    public ImageView getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(Image imageSrc) {
        this.imageSrc = new ImageView();
        this.imageSrc.setImage(imageSrc);
        this.getImageSrc().setLayoutX(this.getX());
        this.getImageSrc().setLayoutY(this.getY());
        this.getImageSrc().setFitWidth(this.getWidth());
        this.getImageSrc().setFitHeight(this.getHeight());
        this.getImageSrc().setPreserveRatio(true);
        this.getImageSrc().setSmooth(true);
        this.getImageSrc().setCache(true);
    }

    public void setRotate(double degree) {
        this.getImageSrc().setRotate(degree);
    }
}