package sample;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;

public class Main extends Application {

    public static Image getImage(String filename) {
        int width = 352, height = 288;

        byte[] fileContent = {0, 0};

        File fi = new File(filename);
        try {
            fileContent = Files.readAllBytes(fi.toPath());
        }catch(Exception e) {
            System.out.println("Oops, read error.");
        }

        BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int count = 0;
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int r = fileContent[count] & 0xff;
                int g = fileContent[count + (width * height)] & 0xff;
                int b = fileContent[count + (2 * width * height)] & 0xff;

                count++;

                int pix = 0xff000000 | (r << 16) | (g << 8) | b;
                bImage.setRGB(x, y, pix);
            }
        }

        Image image = SwingFXUtils.toFXImage(bImage, null);

        System.out.println("Okay");

        return image;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("ImageView Experiment 1");

//        String imageName = "C:\\Users\\abhin\\Desktop\\USC Stuff\\CSCI 576 Multimedia Systems\\Final Project\\Data\\USC\\USC\\USCOne\\USCOne0005.rgb";
////        String imageName = "C:\\Users\\abhin\\Desktop\\Memes\\jeremycarwet.PNG";
//
//        Image image = getImage(imageName);
//
//        ImageView imageView = new ImageView(image);
//
//        HBox hbox = new HBox(imageView);

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
