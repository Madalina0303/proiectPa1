package sample;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AlgorithmController implements Initializable {

    public Rectangle rect;
    public Pane pane;
    public Pane pane1;
    public List<Circle> trips;
    public List<Rectangle> depots;
    public ImageView go;

    public ImageView car;
    public ImageView background;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane1.setBackground(new Background(new BackgroundFill(Color.web("#" + "eaeaea"), CornerRadii.EMPTY, Insets.EMPTY)));
//        BackgroundImage bi=new BackgroundImage(new Image("C:\\Users\\40753\\Desktop\\sosea.png",100,100,false,true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
//                BackgroundSize.DEFAULT);
//        pane1.setBackground(new Background(bi));
    }

    public void takeArguments(javafx.scene.input.MouseEvent actionEvent) throws FileNotFoundException {
        Node n = (Node) (actionEvent.getSource());
        Stage stage = (Stage) (n.getScene().getWindow());

        VehicleData vd = (VehicleData) (stage.getUserData());

        System.out.println(vd.getNrTrips());

        double x, y;
        double stx = 50;
        double sty = 65;
        double dim = (525 / vd.getNrTrips());
        trips = new ArrayList<>();
        depots = new ArrayList<>();

        for (int i = 0; i < vd.getNrTrips(); i++) {
            x = stx + dim * i;
            y = sty + (Math.random() * 120);

            Circle aux = new Circle(x, y, 8);
            pane1.getChildren().add(aux);
            trips.add(aux);
        }
        dim = (525 / vd.getNrDepots());
        for (int i = 0; i < vd.getNrDepots(); i++) {
            x = stx + dim * i;
            y = 250;

            Rectangle r = new Rectangle(x, y, 8, 8);
            pane1.getChildren().add(r);
            depots.add(r);
            Image image = new Image(new FileInputStream("D:\\PA-laborator\\proiectPa1\\src\\sample\\depou.png"));

            //Setting the image view
            ImageView imageView = new ImageView(image);

            //Setting the position of the image
            imageView.setX(x - 15);
            imageView.setY(y - 10);

            //setting the fit height and width of the image view
            imageView.setFitHeight(60);
            imageView.setFitWidth(60);

            //Setting the preserve ratio of the image view
            imageView.setPreserveRatio(true);
            pane1.getChildren().add(imageView);
        }
        vd.solve(pane1, trips, depots);
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(4.0));
        transition.setToX(650);
        transition.setToY(0);

        transition.setNode(car);
        //.setOnFinished(onFinish(actionEvent));
        transition.play();
        // de pus muchii conform lui a  intre cercuri
//        addEdge(vd);
//        draw(vd);
    }

    public void addEdge(VehicleData vd) {

        int[][] a = vd.getA();
        for (int i = 0; i < vd.getNrTrips(); i++) {
            for (int j = 0; j < vd.getNrTrips(); j++) {
                if (a[i][j] == 1) {
                    // gasim centru cercului
                    double x1 = trips.get(i).getCenterX();
                    double y1 = trips.get(i).getCenterY();
                    double x2 = trips.get(j).getCenterX();
                    double y2 = trips.get(j).getCenterY();
                    double raza = trips.get(i).getRadius();
//                    Arrow sageata = new Arrow(x1 + raza, y1, x2 - raza, y2);
//                    pane1.getChildren().add(sageata);

                }
            }
        }
    }

    public void draw(VehicleData vd) {
        int[] sol = vd.getSol();
        /// vrem sa descoperim nodurile care sunt onorate de aceasi masina si intre ele coloram
        for (int j = 0; j < vd.getNrTotal(); j++) {
            for (int i = 0; i < vd.getNrTrips(); i++) {
                if (sol[i] == j) { // masina j onoreaza calatoria i ??

                }
            }
        }
    }


}
