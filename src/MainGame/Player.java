//package MainGame;
//
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//
///**
// * Testing.PopUp.Copter
// * Created by Dan on 2/24/2016.
// */
//public class Player extends Actor {
//    private ImageView src;
//    private double tempX;
//    private double tempY;
//
//    public Player() {
//        src = new ImageView();
//    }
//
//    public Player(Image src) {
//        this.src = new ImageView();
//        this.getSrc2().setImage(src);
//    }
//
//    public ImageView getSrc2() {
//        return src;
//    }
//
//    public void setSrc(Image src) {
//        this.getSrc2().setImage(src);
//    }
//
//    @Override
//    public double getX() {
//        return this.getSrc2().getLayoutX();
//    }
//
//    @Override
//    public void setX(double x) {
//        this.getSrc2().setLayoutX(x);
//        tempX = x;
//    }
//
//    @Override
//    public double getY() {
//        return this.getSrc2().getLayoutY();
//    }
//
//    @Override
//    public void setY(double y) {
//        this.getSrc2().setLayoutY(y);
//        tempY = y;
//    }
//
//    @Override
//    public double getWidth() {
//        return (int) this.getSrc2().getFitWidth();
//    }
//
//    @Override
//    public void setWidth(double width) {
//        this.getSrc2().setFitWidth(width);
//    }
//
//    @Override
//    public void setHeight(double height) {
//        this.getSrc2().setFitHeight(height);
//    }
//
//    @Override
//    public int getHeight() {
//        return (int) this.getSrc2().getFitHeight();
//    }
//
//    @Override
//    public void setBounds(double x, double y, int width, int height) {
//        this.setX(x);
//        this.setY(y);
//        this.setWidth(width);
//        this.setHeight(height);
//    }
//
//    @Override
//    public void move(int xSpeed, int ySpeed) {
//        this.setX(tempX += xSpeed);
//        this.setY(tempY += ySpeed);
//    }
//
//    @Override
//    public void moveX(int xSpeed) {
//        this.setX(tempX += xSpeed);
//    }
//
//    @Override
//    public void moveY(int ySpeed) {
//        this.setY(tempY += ySpeed);
//    }
//
//    public void setRotate(double degree) {
//        this.getSrc2().setRotate(degree);
//    }
//}