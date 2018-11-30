package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ImageView view1;

    private Main main;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String imageName = "C:\\Users\\abhin\\Desktop\\USC Stuff\\CSCI 576 Multimedia Systems\\Final Project\\Data\\USC\\USC\\USCOne\\USCOne0005.rgb";
//        Image image = main.getImage(imageName);
        Image image = null;
        try {
            image = new Image(new FileInputStream("C:\\Users\\abhin\\Desktop\\Memes\\jeremycarwet.PNG"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        view1 = new ImageView(image);
    }
}
