package MainGame;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * MainGame.Copter
 * Created by Dan on 2/24/2016.
 */

public class Controller extends Application {

    Random rand;

    public Group gameLayout;
    private VBox menuLayout;
    public Scene gameScene;
    private Scene menuScene;
    public Actor player;
    private int playerSpeed;

    private Stage primaryStage;
    public List<Actor> barriers;
    private int nBarriers;
    private int barrierGap;
    private int barrierSpeed;
    public Actor topRect;
    public Actor botRect;
    private Text hsText;
    public List<Actor> trail;
    public boolean noclip;

    public int score = 0;
    private int scoreAdder = 0;
    private File highScore;
    private int hs;

    public GameLoop tick = new GameLoop(this);

    // TODO Make barriers save to, read from a file.

    public Controller() {
        noclip = false;
        rand = new Random();
        gameLayout = new Group();
        gameScene = new Scene(gameLayout, 800, 300, Color.BLACK);
        barrierSpeed = -10;
        nBarriers = 200;
        barrierGap = 400;
        highScore = new File("src\\res\\high_score.txt");
        hsText = new Text();
    }

    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        menuLayout = new VBox(20);
        menuScene = new Scene(menuLayout, 300, 300, Color.BLACK);
        Button start = new Button("Start");
        Button quit = new Button("Exit");
        menuLayout.getChildren().addAll(start, quit);
        menuLayout.setStyle("-fx-background-color: black");
        menuLayout.setAlignment(Pos.CENTER);
        start.setOnAction(e -> {
            this.primaryStage.setScene(gameScene);
            try {
                gameStart();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        quit.setOnAction(e -> this.primaryStage.close());

        this.primaryStage.setTitle("Copter");
        this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../images/copter.png")));
        this.primaryStage.setScene(menuScene);
        this.primaryStage.setResizable(false);
        this.primaryStage.show();
    }

    private void gameStart() throws IOException {
        player = new Actor();
        double playerX = 50;
        double playerY = 100;
        barriers = new ArrayList<>();
        topRect = new Actor();
        botRect = new Actor();
        playerSpeed = 5;
        trail = new ArrayList<>();

        if (highScore.length() == 0) {
            FileWriter temp = new FileWriter(highScore, false);
            temp.write("" + 0);
            temp.flush();
            temp.close();
        }
        Scanner hsIn = new Scanner(highScore);
        hsText.setText("High Score: " + hsIn.nextInt());
        hsText.setFont(Font.font(15));
        hsText.setLayoutX(
                gameScene.getWidth() - hsText.getLayoutBounds().getWidth());
        hsText.setLayoutY(25);
        hsText.setFill(Color.WHITE);

        gameLayout.getChildren().add(hsText);

        topRect.setSrc(new Rectangle());
        topRect.setBounds(0, 0, (int) gameScene.getWidth() + 10, 10);
        topRect.setFill(Color.LIME);

        botRect.setSrc(new Rectangle());
        botRect.setBounds(0, 290, (int) gameScene.getWidth() + 10, 10);
        botRect.setFill(Color.LIME);
        gameLayout.getChildren().addAll(topRect.getSrc(), botRect.getSrc());

        player.setSrc(new Rectangle());
        player.setBounds(playerX, playerY, 50, 35);
        player.setImageSrc(new Image(getClass().getResourceAsStream("../images/copter.png")));
        player.setImageLocation(player.getX(), player.getY());
        player.setImageWidth(player.getWidth());
        player.setImageHeight(player.getHeight());
        player.setYSpeed(playerSpeed);
        player.setFill(Color.TRANSPARENT);
        player.getSrc().setStroke(null);
        player.setRotate(20);
        gameLayout.getChildren().addAll(player.getSrc(), player.getImageSrc());

        for (int i = 0; i < nBarriers; i++) {
            barriers.add(new Actor());
            barriers.get(i).setSrc(new Rectangle());
            double x = 0;
            if (i == 0) {
                x = gameScene.getWidth();
            }
            if (i > 0) {
                x = barriers.get(i - 1).getX() + barrierGap;
            }

            double y = rand.nextInt(((int) gameScene.getHeight() - 100) - 10) + 10;

            barriers.get(i).setBounds(x, y, 20, rand.nextInt(100 - 50) + 50);
            barriers.get(i).setFill(Color.LIME);
            barriers.get(i).setXSpeed(barrierSpeed);
            gameLayout.getChildren().add(barriers.get(i).getSrc());

        }
        tick.start();
    }

    public void gameStop() throws IOException {
        tick.stop();
        gameLayout.getChildren().clear();
        for (Actor barrierList : barriers) {
            gameLayout.getChildren().remove(barrierList.getSrc());
        }
        for (Actor trails : trail) {
            gameLayout.getChildren().remove(trails.getSrc());
        }
        handleHighScore();
        score = 0;
        this.primaryStage.setScene(menuScene);
    }

    private void handleHighScore() throws IOException {
        FileWriter writer = new FileWriter(highScore, true);
        Scanner hsIn = new Scanner(new FileReader(highScore));
        if (score > hsIn.nextInt()) {
            FileWriter temp = new FileWriter(highScore, false);
            temp.write("");
            temp.flush();
            temp.close();
            writer.write("" + score);
            writer.flush();
            writer.close();
            hs = score;
        }
    }

    public void handleInput() {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.DOWN) || event.getCode().equals(KeyCode.S)) {
                    player.setYSpeed(playerSpeed);
                    player.setRotate(30);
                }
                if (event.getCode().equals(KeyCode.UP) || event.getCode().equals(KeyCode.W)) {
                    player.setYSpeed(playerSpeed * (-1));
                    player.setRotate(10);
                }
                if (event.getCode() == KeyCode.SPACE) {
                    player.setRotate(10);
                    player.setYSpeed(-5);
                }
                if (event.getCode().equals(KeyCode.Q)) {
                    try {
                        gameStop();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (event.getCode().equals(KeyCode.N)) {
                    noclip = !noclip;
                }
                if (event.getCode().equals(KeyCode.H)) {
                    player.getSrc().setStroke(Color.AQUA);
                    topRect.getSrc().setStroke(Color.AQUA);
                    botRect.getSrc().setStroke(Color.AQUA);
                    for (Actor barrierList : barriers) {
                        barrierList.getSrc().setStroke(Color.CYAN);
                    }
                }
            }
        });
        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                player.setYSpeed(0);
                player.setRotate(20);
                if (event.getCode().equals(KeyCode.SPACE)) {
                    player.setYSpeed(5);
                }
            }
        });
    }

    public void moveBarriers() {
        for (Actor barrierList : barriers) {
            barrierList.moveX(barrierList.getXSpeed());
        }
    }

    public void score() {
        this.primaryStage.setTitle("Copter | " + score);
        if (scoreAdder % 5 == 0) {
            score++;
        }
        scoreAdder++;

    }

}