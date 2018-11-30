package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    private Main main;
    private Image image;
    private String prefix = "C:\\Users\\abhin\\Desktop\\USC Stuff\\CSCI 576 Multimedia Systems\\Final Project\\Data\\USC\\USC\\USCOne\\";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(33.33), new EventHandler<ActionEvent>() {

            private int i = 1;
            String pf2 = "USCOne", imageName = "";

            @Override
            public void handle(ActionEvent event) {
                imageName = prefix + pf2 + String.format("%04d", i) + ".rgb";
//                System.out.println(imageName);
                image = main.getImage(imageName);
                view1.setImage(image);
                i++;
            }
        }));
        timeline.setCycleCount(9000);
        timeline.play();
    }
}
