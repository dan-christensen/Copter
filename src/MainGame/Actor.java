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
    private int xSpeed;
    private int ySpeed;
    private ImageView imageSrc = null;

    private double tempX;
    private double tempY;

    public Rectangle getSrc() {
        return src;
    }

    /**
     * @param src Defines the source rectangle.
     */
    public void setSrc(Rectangle src) {
        this.src = src;
    }

    public double getX() {
        return this.getSrc().getLayoutX();
    }

    /**
     * @param x Used to place the Source Rectangle on the x axis.
     */
    public void setX(double x) {
        this.x = x;
        this.getSrc().setLayoutX(x);
        tempX = x;
    }

    public double getY() {
        return this.getSrc().getLayoutY();
    }

    /**
     * @param y Used to place the Source Rectangle on the y axis.
     */
    public void setY(double y) {
        this.y = y;
        this.getSrc().setLayoutY(y);
        tempY = y;
    }

    public double getWidth() {
        return this.getSrc().getWidth();
    }

    /**
     * @param width Sets the Source Rectangles width.
     */
    public void setWidth(double width) {
        this.width = width;
        this.getSrc().setWidth(width);
    }

    public double getHeight() {
        return this.getSrc().getHeight();
    }

    /**
     * @param height Sets the Source Rectangles height.
     */
    public void setHeight(double height) {
        this.height = height;
        this.getSrc().setHeight(height);
    }


    /**
     * Sets both the x speed and y speed at the same time.
     *
     * @param xSpeed
     * @param ySpeed
     */
    public void setSpeed(int xSpeed, int ySpeed) {
        this.setXSpeed(xSpeed);
        this.setYSpeed(ySpeed);
    }

    public int getXSpeed() {
        return xSpeed;
    }

    /**
     * @param xSpeed Specifically define the x speed.
     */
    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getYSpeed() {
        return ySpeed;
    }

    /**
     * @param ySpeed Specifically define the y speed.
     */
    public void setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    /**
     * @param color Sets the Source rectangles fill color.
     */
    public void setFill(Color color) {
        this.getSrc().setFill(color);
    }

    /**
     * Sets all the layout attributes at one time.
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void setBounds(double x, double y, double width, double height) {
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
    }

    /**
     * Moves the shape based on the x and y speed defined.
     *
     * @param xSpeed
     * @param ySpeed
     */
    public void move(int xSpeed, int ySpeed) {
        this.moveX(xSpeed);
        this.moveY(ySpeed);
    }

    /**
     * @param xSpeed Moves the shape in a based on the x speed.
     */
    public void moveX(int xSpeed) {
        this.setX(tempX += xSpeed);
        if (imageSrc != null) {
            this.moveImageX((int) tempY);
        }
    }

    /**
     * @param ySpeed Moves the shape in a based on the y speed.
     */
    public void moveY(int ySpeed) {
        this.setY(tempY += ySpeed);
        if (imageSrc != null) {
            this.moveImageY((int) tempY);
        }
    }

    public ImageView getImageSrc() {
        return imageSrc;
    }

    /**
     * Adds an image to the source based on
     * the width, and height of
     * the source rectangle.
     *
     * @param imageSrc
     */

    public void setImageSrc(Image imageSrc) {
        this.imageSrc = new ImageView();
        this.imageSrc.setImage(imageSrc);
        this.getImageSrc().setFitWidth(this.getWidth());
        this.getImageSrc().setFitHeight(this.getHeight());
        this.getImageSrc().setPreserveRatio(true);
        this.getImageSrc().setSmooth(true);
        this.getImageSrc().setCache(true);
    }

    /**
     * Sets the image location on the x and y axis.
     *
     * @param x
     * @param y
     */
    public void setImageLocation(double x, double y) {
        this.setImageX(x);
        this.setImageY(y);
    }

    /**
     * @param x Sets the image location on the x axis.
     */
    public void setImageX(double x) {
        this.getImageSrc().setLayoutX(x);
    }

    public double getImageX() {
        return this.getImageSrc().getLayoutX();
    }

    /**
     * @param y Sets the image location on the y axis.
     */
    public void setImageY(double y) {
        this.getImageSrc().setLayoutY(y);
    }

    public double getImageY() {
        return this.getImageSrc().getLayoutY();
    }

    /**
     * @param width Sets the images fit width.
     */
    public void setImageWidth(double width) {
        this.getImageSrc().setFitWidth(width);
    }

    public double getImageWidth() {
        return this.getImageSrc().getFitWidth();
    }

    /**
     * @param height Sets the images fit height.
     */
    public void setImageHeight(double height) {
        this.getImageSrc().setFitHeight(height);
    }

    public double getImageHeight() {
        return this.getImageSrc().getFitHeight();
    }

    /**
     * @param degree Rotates the image based on a given degree.
     */
    public void setRotate(double degree) {
        this.getImageSrc().setRotate(degree);
    }

    /**
     * Moves an image in the given directions based on the
     * source rectangles speed.
     *
     * @param xSpeed
     * @param ySpeed
     */
    public void moveImage(int xSpeed, int ySpeed) {
        this.moveImageX(xSpeed);
        this.moveImageY(ySpeed);
    }


    /**
     * Moves an image in the given directions based on the
     * source rectangles x speed.
     *
     * @param xSpeed
     */
    public void moveImageX(int xSpeed) {
        this.setImageX(xSpeed);
    }

    /**
     * Moves an image in the given directions based on the
     * source rectangles y speed.
     *
     * @param ySpeed
     */
    public void moveImageY(int ySpeed) {
        this.setImageY(ySpeed);
    }
}