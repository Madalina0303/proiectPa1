package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("intro.fxml"));
        primaryStage.setTitle("MDVSP");
        primaryStage.setScene(new Scene(root, 942, 615));

//        String musicFile = "C:\\Users\\40753\\Desktop\\hai.mp3";     // For example
//
//        Media sound = new Media(new File(musicFile).toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(sound);
//        mediaPlayer.setAutoPlay(true);


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
