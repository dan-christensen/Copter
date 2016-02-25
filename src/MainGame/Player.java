package MainGame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Testing.PopUp.Copter
 * Created by Dan on 2/24/2016.
 */
public class Player extends Actor {
    private ImageView src = new ImageView();

    public Player() {
    }

    public Player(ImageView src) {

        this.src = src;
    }

    public ImageView getSrc() {
        return src;
    }

    public void setSrc(Image src) {
        this.getSrc().setImage(src);
    }

    @Override
    public double getX() {
        return super.getX();
    }

    @Override
    public void setX(double x) {
        this.getSrc().setLayoutX(x);
    }

    @Override
    public double getY() {
        return super.getY();
    }

    @Override
    public void setY(double y) {
        this.getSrc().setLayoutY(y);
    }

    @Override
    public int getWidth() {
        return super.getWidth();
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
        return super.getHeight();
    }
}