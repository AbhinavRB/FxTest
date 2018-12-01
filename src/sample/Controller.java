package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private Main main;
    private Image image;
    private boolean isPlaying;
    private Timeline timeline;
    public int currentFrame;
    private String prefix = "C:\\Users\\abhin\\Desktop\\USC Stuff\\CSCI 576 Multimedia Systems\\Final Project\\Data\\USC\\USC\\USCOne\\";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isPlaying = false;
        currentFrame = 1;

        seek1.valueProperty().addListener((observable, oldValue, newValue) -> {
//            double position = Math.floor((double) newValue) * 33.33;
            timeline.pause();
            currentFrame = (int) Math.floor((Double) newValue);
            if (currentFrame > 8999)
                currentFrame = 8999;
            System.out.println(currentFrame);
            timeline.play();
        });

        try {
            videoLoop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void videoLoop() throws InterruptedException {
        String pf2 = "USCOne", imageName = "";
//        for (int i = 1; i <= 20; i++) {
//            imageName = prefix + pf2 + String.format("%04d", i) + ".rgb";
//            System.out.println(imageName);
//            image = main.getImage(imageName);
//            view1.setImage(image);
//            TimeUnit.MILLISECONDS.sleep((long) 33.33);
//        }

        timeline = new Timeline(new KeyFrame(Duration.millis(33.33333), new EventHandler<ActionEvent>() {

            private int i = 1;
            String pf2 = "USCOne", imageName = "";

            @Override
            public void handle(ActionEvent event) {
                imageName = prefix + pf2 + String.format("%04d", currentFrame) + ".rgb";
//                System.out.println(imageName);
                image = main.getImage(imageName);
                view1.setImage(image);
                currentFrame++;
                if (currentFrame >= 8999)
                    timeline.stop();
                i++;
            }
        }));
        isPlaying = true;
        timeline.setCycleCount(9000);
        timeline.play();

        System.out.println("Um, is this after?");
    }

    @FXML
    public void playPauseToggle() {
        if (isPlaying) {
            timeline.pause();
            isPlaying = false;
        }
        else {
            timeline.play();
            isPlaying = true;
        }
    }


}
