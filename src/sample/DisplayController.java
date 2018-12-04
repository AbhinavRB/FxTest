package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class DisplayController implements Initializable {

    @FXML
    private ImageView displayView1;
    @FXML
    private ImageView displayView2;
    @FXML
    private Slider displaySeek1;
    @FXML
    private Slider displaySeek2;
    @FXML
    private Button disPlayButton;
    @FXML
    private Button disPauseButton;
    @FXML
    private Canvas displayCanvas;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
