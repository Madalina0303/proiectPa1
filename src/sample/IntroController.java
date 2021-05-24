package sample;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IntroController implements Initializable {
    public AnchorPane st;
    public AnchorPane dr;
    public Rectangle start;
    public Rectangle sound;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        st.setBackground(new Background(new BackgroundFill(Color.web("#" + "82b5e0"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void nextStage(MouseEvent mouseEvent) {

        Parent algorithm = null;
        try {
            algorithm = FXMLLoader.load(getClass().getResource("sample.fxml"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Scene scene2 = new Scene(algorithm);
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

        window.setScene(scene2);
        window.show();
    }

    public void changeColor(MouseEvent mouseEvent) {
        start.setFill(Color.valueOf("#a6bc27"));
        System.out.println("pe inauntru !!!");
    }

    public void changecolorBack(MouseEvent mouseEvent) {
        if (!((mouseEvent.getX() >= start.getX() + start.getLayoutX() && mouseEvent.getX() <= start.getX() + start.getLayoutX() + start.getWidth() &&
                mouseEvent.getY() >= start.getY() + start.getLayoutY() && mouseEvent.getY() <= start.getY() + start.getLayoutY() + start.getHeight()
        ))) {
            start.setFill(Color.valueOf("#2a7cc9"));
        }
        if (!(mouseEvent.getX() >= sound.getX() + sound.getLayoutX() && mouseEvent.getX() <= sound.getX() + sound.getLayoutX() + sound.getWidth() &&
                mouseEvent.getY() >= sound.getY() + sound.getLayoutY() && mouseEvent.getY() <= sound.getY() + sound.getLayoutY() + sound.getHeight())) {
            sound.setFill(Color.valueOf("#2a7cc9"));
        }

    }

    public void playSound(MouseEvent mouseEvent) {

        String musicFile = "C:\\Users\\40753\\Desktop\\hai.mp3";     // For example

        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

    }

    public void changeColorSound(MouseEvent mouseEvent) {
        sound.setFill(Color.valueOf("#a6bc27"));
    }
}
