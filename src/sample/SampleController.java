package sample;


import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class SampleController implements Initializable {
    public int nrChecked1 = 0;
    public int nrChecked2 = 0;
    public Color color = Color.CHOCOLATE;
    public int nrClick = 0;
    public int nrDTrip = 0; // de la duplicated /souble nr de tripuri
    public int nrDepot = 0; // nr de depozite
    public int[] nrCars = new int[3]; // nr de masini ale depozitelor checuite
    public boolean[] clicked = new boolean[29];
    public Line florilor;
    public Line pacii;
    public Line rozelor;
    public Line muncii;
    public Line garii;
    public Line trandafirilor;
    public Line oituz;
    public Line plopilor;
    public Line mai;
    public Line blaj;
    public Line arcu;
    public Line viitorului;
    public Line timpului;
    public Line mosu;
    public Line eclipsei;
    public Line soarelui;
    public Line poetului;
    public Line an2021;
    public Line alpin;
    public Line zorilor;
    public Line soldatilor;
    public Line izvor;
    public Line columnei;
    public Line an1914;
    public Line victoriei;
    public Line cicoarei;
    public Line pitagora;
    public Line gloriei;
    public Line alba;
    public Line tei;
    public Line viitor;
    public Line olimp;
    public Line fericirii;
    public Line morii;
    public Line independentei;
    public Line sforii;
    public Line padurii;
    public Line paun;
    public Line alun;
    public Line noptii;
    public Line vitejilor;
    public Line aviatorului;
    public Line eroilor;
    public Spinner cars2;
    public CheckBox d1;
    public Spinner cars1;
    public CheckBox d2;
    public Spinner cars3;
    public CheckBox d3;
    public ImageView semafor;
    public ImageView masina;
    @FXML
    private Pane pane1;
    public Pane pane2;
    public Circle c1;
    public Circle c2;
    public Circle c3;
    public Circle c4;
    public Circle c5;
    public Circle c6;
    public Circle c7;
    public Circle c8;
    public Circle c9;
    public Circle c10;
    public Circle c11;
    public Circle c12;
    public Circle c13;
    public Circle c14;
    public Circle c15;
    public Circle c16;
    public Circle c17;
    public Circle c18;
    public Circle c19;
    public Circle c20;
    public Circle c21;
    public Circle c22;
    public Circle c23;
    public Circle c24;
    public Circle c25;
    public Circle c26;
    public Circle c27;
    public Circle c28;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      //  pane2.setBackground(new Background(new BackgroundFill(Color.web("#" + "ff4d4d"), CornerRadii.EMPTY, Insets.EMPTY)));
        draw(florilor);
        draw(rozelor);
      //  playSound();
        pane2.setTranslateX(-700);

        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(4.0));
        transition.setToX(70);
        transition.setToY(0);

        transition.setNode(pane2);
        //.setOnFinished(onFinish(actionEvent));
        transition.play();

    }

    public void draw(Line street) {
        double[] coordX = new double[5];
        double[] coordY = new double[5];
        // start
        coordX[0] = street.getLayoutX() + street.getStartX();
        coordY[0] = street.getLayoutY() + street.getStartY();

        // finish
        coordX[4] = street.getLayoutX() + street.getEndX();
        coordY[4] = street.getLayoutY() + street.getEndY();
        // aflam mijlocul principal
        coordX[2] = (coordX[0] + coordX[4]) / 2.0;
        coordY[2] = (coordY[0] + coordY[4]) / 2.0;
        // aflam mijlocul mai din stanga
        coordX[1] = (coordX[0] + coordX[2]) / 2.0;
        coordY[1] = (coordY[0] + coordY[2]) / 2.0;

        // aflam mijlocul din dreapta
        coordX[3] = (coordX[2] + coordX[4]) / 2.0;
        coordY[3] = (coordY[2] + coordY[4]) / 2.0;

//        double xs = alba.getLayoutX() + alba.getStartX();
//        double ys = alba.getLayoutY() + alba.getStartY();
//        double xf = alba.getLayoutX() + alba.getEndX();
//        double yf = alba.getLayoutY() + alba.getEndY();
//        double xm = (xs + xf) / (2.0);
//        double ym = (ys + yf) / (2.0);


//        LineThread lt = new LineThread(coordX,coordY,pane2);
//        new Thread(lt).start();
        int i = 0;
        Timeline timeLine = new Timeline(

                new KeyFrame(
                        Duration.seconds(3),
                        (ActionEvent actionEvent) -> {
                            Line line1 = new Line(coordX[0], coordY[0], coordX[1], coordY[1]);
                            line1.setStroke(Color.LIGHTBLUE);
                            //System.out.println(p.getChildren().add(line1));
                            pane2.getChildren().addAll(line1);

                        }
                ),
                new KeyFrame(
                        Duration.seconds(4),
                        (ActionEvent actionEvent) -> {
                            Line line1 = new Line(coordX[1], coordY[1], coordX[2], coordY[2]);
                            line1.setStroke(Color.LIGHTBLUE);
                            //System.out.println(p.getChildren().add(line1));
                            pane2.getChildren().addAll(line1);

                        }
                ),


                new KeyFrame(
                        Duration.seconds(5),
                        (ActionEvent actionEvent) -> {
                            Line line1 = new Line(coordX[2], coordY[2], coordX[3], coordY[3]);
                            line1.setStroke(Color.LIGHTBLUE);
                            //System.out.println(p.getChildren().add(line1));
                            pane2.getChildren().addAll(line1);

                        }
                ),
                new KeyFrame(
                        Duration.seconds(6),
                        (ActionEvent actionEvent) -> {
                            Line line1 = new Line(coordX[3], coordY[3], coordX[4], coordY[4]);
                            line1.setStroke(Color.LIGHTBLUE);
                            //System.out.println(p.getChildren().add(line1));
                            pane2.getChildren().addAll(line1);

                        }
                )

        );
        timeLine.setCycleCount(1);
        System.out.println("face si gata");
        timeLine.play();

    }


    public void change(javafx.scene.input.MouseEvent mouseEvent) {


        Circle c = new Circle();
        c.setId("gol");

        if (mouseEvent.getSource() == c1 && !clicked[1]) {
            c = c1;
            clicked[1] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c2 && !clicked[2]) {
            c = c2;
            clicked[2] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c3 && !clicked[3]) {
            c = c3;
            clicked[3] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c4 && !clicked[4]) {
            c = c4;
            clicked[4] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c5 && !clicked[5]) {
            c = c5;
            clicked[5] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c6 && !clicked[6]) {
            c = c6;
            clicked[6] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c7 && !clicked[7]) {
            c = c7;
            clicked[7] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c8 && !clicked[8]) {
            c = c8;
            clicked[8] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c9 && !clicked[9]) {
            c = c9;
            clicked[9] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c10 && !clicked[10]) {
            c = c10;
            clicked[10] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c11 && !clicked[11]) {
            c = c11;
            clicked[11] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c12 && !clicked[12]) {
            c = c12;
            clicked[12] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c13 && !clicked[13]) {
            c = c13;
            clicked[13] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c14 && !clicked[14]) {
            c = c14;
            clicked[14] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c15 && !clicked[15]) {
            c = c15;
            clicked[15] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c16 && !clicked[16]) {
            System.out.println("16!!!!!!!!!!!!!!!!!!!!!!!");
            c = c16;
            clicked[16] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c17 && !clicked[17]) {
            c = c17;
            clicked[17] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c18 && !clicked[18]) {
            c = c18;
            clicked[18] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c19 && !clicked[19]) {
            c = c19;
            clicked[19] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c20 && !clicked[20]) {
            c = c20;
            clicked[20] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c21 && !clicked[21]) {
            c = c21;
            clicked[21] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c22 && !clicked[22]) {
            c = c22;
            clicked[5] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c23 && !clicked[23]) {
            c = c23;
            clicked[5] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c24 && !clicked[24]) {
            c = c24;
            clicked[24] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c25 && !clicked[25]) {
            c = c25;
            clicked[5] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c26 && !clicked[26]) {
            c = c26;
            clicked[5] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c27 && !clicked[27]) {
            c = c27;
            clicked[27] = true;
            nrDTrip++;
            c.setId("plin");
        } else if (mouseEvent.getSource() == c28 && !clicked[28]) {
            c = c28;
            clicked[28] = true;
            nrDTrip++;
            c.setId("plin");
        }
        if (c.getId().equals("plin")) {
            System.out.println("da");
            c.setFill(color);
            nrClick++;
            if (nrClick == 2) {
                randomColor();
                nrClick = 0;
            }
        }
    }


    public void randomColor() {

        Color col = Color.color(Math.random(), Math.random(), Math.random());
        setColor(col);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void checked1(ActionEvent actionEvent) {

//        nrChecked1++;
//        if(nrChecked1==1)
//            nrDepot++;
//        else{
//            nrDepot--;
//            nrChecked1=0;
//        }
    }

    public void checked2(ActionEvent actionEvent) {
//        nrChecked2++;
//        if(nrChecked2==1)
//            nrDepot++;
//        else{
//            nrDepot--;
//            nrChecked2=0;
//        }

    }



    public void next(javafx.scene.input.MouseEvent actionEvent) throws IOException {
        // playSound();
        if (d1.isSelected()) {

            nrCars[0] = (int) cars1.getValue();
            System.out.println("e check primu " + nrCars[0]);
            nrDepot++;
        }
        if (d2.isSelected()) {

            nrCars[1] = (int) cars2.getValue();
            System.out.println("e check al 2 lea " + nrCars[1]);
            nrDepot++;
        }
        if (d3.isSelected()) {

            nrCars[2] = (int) cars3.getValue();
            System.out.println("e check al 3 lea " + nrCars[2]);
            nrDepot++;
        }

        System.out.println("nr de depozite este " + nrDepot);
//        // face ceva de trecut mai departe // pe alt panel gol
//        double x1 = masina.getX() + masina.getLayoutX();
//        double y1 = masina.getY() + masina.getLayoutY();
        double x2 = semafor.getLayoutX() + semafor.getX();
        double y2 = semafor.getY() + semafor.getLayoutY();
//        Line line = new Line(x1, y1, x2, y2);
//        pane2.getChildren().add(line);
//        PathTransition pathTransition = new PathTransition();
//        pathTransition.setNode( new Circle(5));
//        pathTransition.setDuration(Duration.seconds(10));
//        pathTransition.setPath( new Circle(100));
//        pathTransition.setCycleCount(PathTransition.INDEFINITE);
//        pathTransition.play();

        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(4.0));
        transition.setToX(590);
        transition.setToY(-10);

        transition.setNode(masina);
        //.setOnFinished(onFinish(actionEvent));
        transition.play();
        transition.setOnFinished(e -> {
            VehicleData vehicleData = new VehicleData(nrDTrip / 2, nrDepot, nrCars);
            Parent algorithm = null;
            try {
                algorithm = FXMLLoader.load(getClass().getResource("sample1.fxml"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Scene scene2 = new Scene(algorithm);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setUserData(vehicleData);
            window.setScene(scene2);
            window.show();
        });


    }


}
