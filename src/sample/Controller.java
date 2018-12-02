package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class Controller implements Initializable {

    @FXML
    private ImageView view1;
    @FXML
    private Slider seek1;
    @FXML
    private ImageView view2;
    @FXML
    private Slider seek2;
    @FXML
    private Canvas canvas1;
    @FXML
    private Button playpauseButton1;
    @FXML
    private Button playPauseButton2;

    private Main main;
    private Image[] image;
    private boolean[] isPlaying;
    private Timeline[] timeline;
    public int[] currentFrame;

    private String prefix = "C:\\Users\\abhin\\Desktop\\USC Stuff\\CSCI 576 Multimedia Systems\\Final Project\\Data\\USC\\USC\\USCOne\\";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isPlaying = new boolean[2];
        currentFrame = new int[2];
        image = new Image[2];
        timeline = new Timeline[2];
        isPlaying[0] = false;
        isPlaying[1] = false;
        String videoOne = "C:\\Users\\abhin\\Desktop\\USC Stuff\\CSCI 576 Multimedia Systems\\Final Project\\Data\\USC\\USC\\USCOne\\USCOne";
        String videoTwo = "C:\\Users\\abhin\\Desktop\\USC Stuff\\CSCI 576 Multimedia Systems\\Final Project\\Data\\USC\\USC\\USCTwo\\USCTwo";

        seek1.valueProperty().addListener((observable, oldValue, newValue) -> {
//            double position = Math.floor((double) newValue) * 33.33;
            timeline[0].pause();
            currentFrame[0] = (int) Math.floor((Double) newValue);
            if (currentFrame[0] > 8999)
                currentFrame[0] = 8999;
//            System.out.println(currentFrame);
            timeline[0].play();
        });

        seek2.valueProperty().addListener((observable, oldValue, newValue) -> {
//            double position = Math.floor((double) newValue) * 33.33;
            timeline[1].pause();
            currentFrame[1] = (int) Math.floor((Double) newValue);
            if (currentFrame[1] > 8999)
                currentFrame[1] = 8999;
//            System.out.println(currentFrame);
            timeline[1].play();
        });

        canvas1.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
//                        if (t.getClickCount() >1) {
//                            reset(canvas, Color.BLUE);
//                        }
                        System.out.println(t.getX() + ", " + t.getY() + ", " + currentFrame[0]);

                        GraphicsContext gc = canvas1.getGraphicsContext2D();
                        gc.clearRect(0, 0, canvas1.getWidth(), canvas1.getHeight());
                        gc.setGlobalAlpha(0.5);
                        gc.setFill(Color.valueOf("#c0c0c0"));
                        gc.fillRect(t.getX() - 40, t.getY() - 40, 80, 80);
                    }
                });

        try {
            videoLoop(videoOne, 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            videoLoop(videoTwo, 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timeline[0].play();
        timeline[1].play();
    }

    public void videoLoop(String videoPrefix, int videoNumber) throws InterruptedException {
        currentFrame[videoNumber] = 1;

        timeline[videoNumber] = new Timeline(new KeyFrame(Duration.millis(33.33333), new EventHandler<ActionEvent>() {

            private int i = 1;
            String imageName = "";

            @Override
            public void handle(ActionEvent event) {
                imageName = videoPrefix + String.format("%04d", currentFrame[videoNumber]) + ".rgb";
//                System.out.println(imageName);
                image[videoNumber] = main.getImage(imageName);
                if (videoNumber == 0)
                    view1.setImage(image[videoNumber]);
                else
                    view2.setImage(image[videoNumber]);
                currentFrame[videoNumber]++;
                if (currentFrame[videoNumber] >= 8999)
                    timeline[videoNumber].stop();
                i++;
            }
        }));
        isPlaying[videoNumber] = true;
        timeline[videoNumber].setCycleCount(9000);
//        timeline.play();
    }

    @FXML
    public void playPauseToggle1() {
        if (isPlaying[0]) {
            timeline[0].pause();
            isPlaying[0] = false;
            playpauseButton1.setText("\u25b6");
        }
        else {
            timeline[0].play();
            isPlaying[0] = true;
            playpauseButton1.setText("\u23f8");
        }
    }

    @FXML
    public void playPauseToggle2() {
        if (isPlaying[1]) {
            timeline[1].pause();
            isPlaying[1] = false;
            playPauseButton2.setText("\u25b6");
        }
        else {
            timeline[1].play();
            isPlaying[1] = true;
            playPauseButton2.setText("\u23f8");
        }
    }
}
