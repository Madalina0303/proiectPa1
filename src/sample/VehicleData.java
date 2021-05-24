package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// in graf nodul cu eticheta nrTrps va fi depozitul !!!!
// incepand cu etichetele de la nrTrips pana la nrTrips+ nrDepot vom avea  nodurile depozit ??


public class VehicleData {
    public Color[] colors;
    int nrTrips;
    int nrDepots;
    int[] nrCars; // nr de masini dintr un depozit
    int nrTotal;
    int[] viz; // viz[i]=1 calatpria i este asignata deja
    int[] sol; // sol[i] = k inseamna calatoria i este facuta de vehiculul k
    int[] cars; // masinile
    int[] startTime; // timpul de start pt calatorii
    int[] endTime; // timpul de final pt calatorii
    int[] trips; // calatoriile in sine
    int[][] time; // matrice de timp de la calatoria i la calatoria j
    int[][] cost; // matricea de cost de la calatoria i la calatoria j
    List<Integer> disponibil; // la inceput toate sunt disponibile;
    // masina Curenta
    int[][] a; // a[i][j]=1 daca endTime[i]+time[i][j]<= startTime[j]
    int[] costExit; // cost iesire din depozit
    int[] costEnter; // cost intrare in depozit

    // calatorii fezabile
    // de la calatoria i imi ajunge timpul sa ajung la calatoria j ???
    public VehicleData(int nrTrips, int nrDepots, int[] nrCars) {
        this.nrTrips = nrTrips;
        this.nrDepots = nrDepots;
        this.nrCars = nrCars;

    }

    public int getNrDepots() {
        return nrDepots;
    }

    public int getNrTrips() {
        return nrTrips;
    }

    public int[] getNrCars() {
        return nrCars;
    }

    public void solve(Pane panel, List<Circle> circleList, List<Rectangle> depots) {

        totalVehicle();
        init();
        //   valorileNoastre();
        for (int i = 0; i < nrTrips; i++) {
            if (viz[i] == 0) { // calatoria nu e asignata
                System.out.println("Tripul " + i + " nu este asignat ");
                int masina = isDisponibil(); // obtinem prima masina disponibila daca exista
                System.out.println("Uite ce masini sunt disponibile ");
                for (Integer j : disponibil)
                    System.out.print(j + " ");
                System.out.println();
                if (masina != -1) {
                    // desanam arc de la depozit la nodul i neasignat
                    paintDtC(depozit(masina), i, colors[masina], panel, circleList, depots);
                    disponibil.remove(disponibil.indexOf(masina));
                    System.out.println("Uite ce masini sunt disponibile dupa ce eliminat masina" + masina);
                    for (Integer j : disponibil)
                        System.out.print(j + " ");
                    System.out.println();
                    assign(i, masina, panel, circleList, depots);

                } else {
                    // nu se poate onora calatoria
                    // slabe sanse sa se ajunga aici, speram
                    System.out.println("OOOPssss, nu e nicio masina disponibila");
                }
            }
        }
        afis();
    }

    public void assign(int calatorie, int masina, Pane pane, List<Circle> circleList, List<Rectangle> rectangleList) {
        viz[calatorie] = 1;
        sol[calatorie] = masina;
        // cautam in continoare vecinii calatorii din matricea a sa vedem daca vreunul poate fi onorata tot
        // aceeasi masina
        int nrNh = 0; // nr vecini
        int[] vec = new int[nrTrips];
        for (int i = 0; i < nrTrips; i++) { // dintre toate astea  o cauatam pe cea cu costul cel mai mic
            if (a[calatorie][i] == 1 && viz[i] == 0) {
                vec[nrNh] = i;
                nrNh++;
            }
        }

        if (nrNh > 0) {
            // obtinem vecinul cu costul cel mai mic
            int min = cost[calatorie][vec[0]];
            int vecOptim = vec[0];
            for (int i = 1; i < nrNh; i++) {
                if (cost[calatorie][vec[i]] < min) {
                    min = cost[calatorie][vec[i]];
                    vecOptim = vec[i];
                }
            }
            // verificam cum este mai IEFTIN pt compania de transport  in cazul in care la depou sunt masini care stau adica
            // comparam min cu( costul de la calatoria i  la depozit  + costul de la depozit la calatoria vecOptim )
            // timpul este neglijat in aceste conditii , din moment ce vine o masina noua
            int dispo = isDisponibil();
            if (dispo != -1) {
                if (costEnter[calatorie] + costExit[vecOptim] >= min) {
                    // calatoria vecOptim este onorata de acelasi vehicul
                    // de la calatorie la vecOptim
                    paint(calatorie, vecOptim, colors[masina], pane, circleList);
                    System.out.println("e ok.. continui eu ");
                    assign(vecOptim, masina, pane, circleList, rectangleList);

                } else {
                    // de la calatorie la depozit si de la depozit la vecin optim
                    System.out.println("Ma duc la depou, vine altul");

                    disponibil.remove(disponibil.indexOf(dispo)); // pe 1 il da afara, il scoate pe traseu

                    disponibil.add(masina); // pe 0 il duce la traseu
                    paintCtD(calatorie, depozit(masina), colors[masina], pane, circleList, rectangleList);
                    paintDtC(depozit(dispo), vecOptim, colors[dispo], pane, circleList, rectangleList);
                    assign(vecOptim, dispo, pane, circleList, rectangleList);
                }
            }

        } else {
            // din punctul asta nu mai avem unde pleca
            // trebuie masini noi
            paintCtD(calatorie, depozit(masina), Color.BLACK, pane, circleList, rectangleList);
            System.out.println("Gata cu assign");
        }
    }

    public void totalVehicle() {

        for (int i = 0; i < nrDepots; i++)
            nrTotal = nrTotal + nrCars[i];

        // return nrTotal;
    }

    public void init() {
        colors = new Color[nrTotal];
        for (int i = 0; i < nrTotal; i++) {
            colors[i] = Color.color(Math.random(), Math.random(), Math.random());
        }
        disponibil = new ArrayList<>(nrTotal);
        for (int i = 0; i < nrTotal; i++)
            disponibil.add(i);  // la inceput toate sunt disponibile
        cars = new int[nrTotal];
        for (int i = 0; i < nrTotal; i++)
            cars[i] = i;
        viz = new int[nrTrips];
        Arrays.fill(viz, 0);
        sol = new int[nrTrips];
        Arrays.fill(sol, -1);
        startTime = new int[nrTrips];
        endTime = new int[nrTrips];
        trips = new int[nrTrips];
        costExit = new int[nrTrips];
        costEnter = new int[nrTrips];
        a = new int[nrTrips][nrTrips];
        for (int i = 0; i < nrTrips; i++) {
            trips[i] = i;
            startTime[i] = (int) (Math.random() * 23);
            endTime[i] = startTime[i] + (int) (Math.random() * 2) + 1;
        }
        sortTrip();
        fillMatrix();
        initCostDepot();
    }

    public void sortTrip() {
        for (int i = 0; i < nrTrips - 1; i++) {
            for (int j = i + 1; j < nrTrips; j++) {
                if (startTime[i] > startTime[j]) {
                    int aux = startTime[i];
                    startTime[i] = startTime[j];
                    startTime[j] = aux;
                    aux = endTime[i];
                    endTime[i] = endTime[j];
                    endTime[j] = aux;
                    aux = trips[i];
                    trips[i] = trips[j];
                    trips[j] = aux;
                }
            }
        }
    }

    public void fillMatrix() {
        randomFill();
        // ne umplem matricea a;
        for (int i = 0; i < nrTrips; i++) {
            for (int j = 0; j < nrTrips; j++)
                a[i][j] = 0;
        }

        for (int i = 0; i < nrTrips - 1; i++) {
            for (int j = i + 1; j < nrTrips; j++) {
                if (endTime[i] + time[i][j] <= startTime[j])
                    a[i][j] = 1;
            }
        }
    }

    public void randomFill() {
        time = new int[nrTrips][nrTrips];
        cost = new int[nrTrips][nrTrips];
        for (int i = 0; i < nrTrips; i++) {
            for (int j = 0; j < nrTrips; j++) {
                if (i != j) {
                    time[i][j] = (int) (Math.random() * 5);
                    cost[i][j] = (int) (Math.random() * 9);
                } else {
                    time[i][j] = 0;
                    cost[i][j] = 0;
                }
            }
        }

    }

    public void initCostDepot() {
        for (int i = 0; i < nrTrips; i++) {
            costExit[i] = (int) (Math.random() * 9);
            costEnter[i] = costExit[i];
        }
    }

    public int[] getSol() {
        return sol;
    }

    public void afis() {
        System.out.println("Nr de calatorii " + nrTrips);
        System.out.println("Nr total de masini " + nrTotal);
        System.out.println();
        System.out.println("Timpul de start si timpul de end pt calatorii ");
        for (int i = 0; i < nrTrips; i++) {
            System.out.println("Calatoria " + i + " are timpul de start " + startTime[i] + " iar de end " + endTime[i]);
        }
        System.out.println();
        System.out.println("Matricea a cu vecinii ");
        for (int i = 0; i < nrTrips; i++) {
            for (int j = 0; j < nrTrips; j++)
                System.out.print(a[i][j]);
            System.out.println();
        }
        System.out.println();
        System.out.println("Matricea de costuri ");
        for (int i = 0; i < nrTrips; i++) {
            for (int j = 0; j < nrTrips; j++)
                System.out.print(cost[i][j]);
            System.out.println();
        }
        System.out.println();
        System.out.println("Matricea timp cu vecinii ");
        for (int i = 0; i < nrTrips; i++) {
            for (int j = 0; j < nrTrips; j++)
                System.out.print(time[i][j]);
            System.out.println();
        }
        System.out.println("Intrare/Iesire depozit");
        for (int i = 0; i < nrTrips; i++)
            System.out.print(costEnter[i]);

        System.out.println("Solutia!!!");
        for (int i = 0; i < nrTrips; i++)
            System.out.print("Calatoria " + i + " servita de " + sol[i]);

    }

    public int isDisponibil() {
        if (disponibil.size() > 0)
            return disponibil.get(0);
        else
            return -1;
    }

    public int[][] getA() {
        return a;
    }

    public void valorileNoastre() {
        disponibil = new ArrayList<>();
        for (int i = 0; i < nrTotal; i++)
            disponibil.add(i);
        viz = new int[nrTrips];
        Arrays.fill(viz, 0);
        sol = new int[nrTrips];
        Arrays.fill(sol, -1);
        startTime = new int[nrTrips];
        endTime = new int[nrTrips];
        a = new int[nrTrips][nrTrips];
        cost = new int[nrTrips][nrTrips];
        time = new int[nrTrips][nrTrips];
        costEnter = new int[nrTrips];
        costExit = new int[nrTrips];
        cars = new int[nrTotal];
        trips = new int[nrTrips];
        for (int i = 0; i < nrTotal; i++)
            cars[i] = i;      //de la 0 la nrCars[0] -1 avem id uirile la masinile din primul depozit
        // la  nrCars[i-1] la  suma nrCars[i-1]+nrCars[i]-1 avem ide uri de la masinile la depozitul i


        for (int i = 0; i < nrTrips; i++)
            trips[i] = i;
        startTime[0] = 12;
        endTime[0] = 14;
        startTime[1] = 14;
        endTime[1] = 16;
        startTime[2] = 15;
        endTime[2] = 17;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                a[i][j] = 0;

        a[0][1] = 1;
        cost[0][0] = 8;
        cost[0][1] = 4;
        cost[0][2] = 2;
        cost[1][0] = 5;
        cost[1][1] = 7;
        cost[1][2] = 2;
        cost[2][0] = 7;
        cost[2][1] = 4;
        cost[2][2] = 1;
        time[0][0] = 2;
        time[0][1] = 0;
        time[0][2] = 2;
        time[1][0] = 2;
        time[1][1] = 0;
        time[1][2] = 2;
        time[2][0] = 1;
        time[2][1] = 3;
        time[2][2] = 3;
        //127
        costEnter[0] = 1;
        costExit[0] = 1;
        costEnter[1] = 2;
        costExit[1] = 2;
        costEnter[2] = 7;
        costExit[2] = 7;
    }

    public int getNrTotal() {
        return nrTotal;
    }

    public void paint(int indice1, int indice2, Color color, Pane p, List<Circle> circleList) {


        double x1 = circleList.get(indice1).getCenterX();
        double y1 = circleList.get(indice1).getCenterY();
        double x2 = circleList.get(indice2).getCenterX();
        double y2 = circleList.get(indice2).getCenterY();
        double raza = circleList.get(indice1).getRadius();
        circleList.get(indice2).setFill(color);
        // Arrow sageata = new Arrow(x1 + raza, y1, x2 - raza, y2);
        draw(x1 + raza, y1, x2 - raza, y2, p, color);
    }

    public void paintDtC(int indice1, int indice2, Color color, Pane p, List<Circle> circleList, List<Rectangle> rectangleList) {

        double x1 = rectangleList.get(indice1).getX();
        double y1 = rectangleList.get(indice1).getY();
        double x2 = circleList.get(indice2).getCenterX();
        double y2 = circleList.get(indice2).getCenterY();
        circleList.get(indice2).setFill(color);
        double raza = circleList.get(indice1).getRadius();
        draw(x1 + 2*raza, y1-raza, x2, y2 + raza, p, color);
    }

    public void paintCtD(int indice1, int indice2, Color color, Pane p, List<Circle> circleList, List<Rectangle> rectangleList) {
        double x1 = circleList.get(indice1).getCenterX();
        double y1 = circleList.get(indice1).getCenterY();
        double x2 = rectangleList.get(indice2).getX();
        double y2 = rectangleList.get(indice2).getY();
        double raza = circleList.get(indice1).getRadius();
        drawSimplu(x1 + raza, y1, x1 + 4 * raza, y1, p, color);
        draw(x1 + 4 * raza, y1, x2 - raza + 10, y2, p, color);
    }

    public void draw(double x1, double y1, double x2, double y2, Pane pane2, Color c) {
        double[] coordX = new double[5];
        double[] coordY = new double[5];
        // start
        coordX[0] = x1;
        coordY[0] = y1;

        // finish
        coordX[4] = x2;
        coordY[4] = y2;
        // aflam mijlocul principal
        coordX[2] = (coordX[0] + coordX[4]) / 2.0;
        coordY[2] = (coordY[0] + coordY[4]) / 2.0;
        // aflam mijlocul mai din stanga
        coordX[1] = (coordX[0] + coordX[2]) / 2.0;
        coordY[1] = (coordY[0] + coordY[2]) / 2.0;

        // aflam mijlocul din dreapta
        coordX[3] = (coordX[2] + coordX[4]) / 2.0;
        coordY[3] = (coordY[2] + coordY[4]) / 2.0;


        Timeline timeLine = new Timeline(

                new KeyFrame(
                        Duration.seconds(2),
                        (ActionEvent actionEvent) -> {
                            Line line1 = new Line(coordX[0], coordY[0], coordX[1], coordY[1]);
                            line1.setStroke(c);
                            System.out.println("1!!");
                            pane2.getChildren().addAll(line1);

                        }
                ),
                new KeyFrame(
                        Duration.seconds(3),
                        (ActionEvent actionEvent) -> {
                            Line line1 = new Line(coordX[1], coordY[1], coordX[2], coordY[2]);
                            line1.setStroke(c);
                            //System.out.println(p.getChildren().add(line1));
                            pane2.getChildren().addAll(line1);

                        }
                ),


                new KeyFrame(
                        Duration.seconds(4),
                        (ActionEvent actionEvent) -> {
                            Line line1 = new Line(coordX[2], coordY[2], coordX[3], coordY[3]);
                            line1.setStroke(c);
                            //System.out.println(p.getChildren().add(line1));
                            pane2.getChildren().addAll(line1);

                        }
                ),
                new KeyFrame(
                        Duration.seconds(5),
                        (ActionEvent actionEvent) -> {
                            Line line1 = new Line(coordX[3], coordY[3], coordX[4], coordY[4]);
                            line1.setStroke(c);
                            //System.out.println(p.getChildren().add(line1));
                            pane2.getChildren().addAll(line1);
                            Arrow sageata = new Arrow(x1, y1, x2, y2, 5.0, c);
                            pane2.getChildren().add(sageata);
                        }
                )

        );
        timeLine.setCycleCount(1);
        //System.out.println("face si gata");
        timeLine.play();

    }

    public void drawSimplu(double x1, double y1, double x2, double y2, Pane pane2, Color c) {
        double[] coordX = new double[5];
        double[] coordY = new double[5];
        // start
        coordX[0] = x1;
        coordY[0] = y1;

        // finish
        coordX[4] = x2;
        coordY[4] = y2;
        // aflam mijlocul principal
        coordX[2] = (coordX[0] + coordX[4]) / 2.0;
        coordY[2] = (coordY[0] + coordY[4]) / 2.0;
        // aflam mijlocul mai din stanga
        coordX[1] = (coordX[0] + coordX[2]) / 2.0;
        coordY[1] = (coordY[0] + coordY[2]) / 2.0;

        // aflam mijlocul din dreapta
        coordX[3] = (coordX[2] + coordX[4]) / 2.0;
        coordY[3] = (coordY[2] + coordY[4]) / 2.0;


        Timeline timeLine = new Timeline(

                new KeyFrame(
                        Duration.seconds(2),
                        (ActionEvent actionEvent) -> {
                            Line line1 = new Line(coordX[0], coordY[0], coordX[1], coordY[1]);
                            line1.setStroke(c);
                            System.out.println("1!!");
                            pane2.getChildren().addAll(line1);

                        }
                ),
                new KeyFrame(
                        Duration.seconds(3),
                        (ActionEvent actionEvent) -> {
                            Line line1 = new Line(coordX[1], coordY[1], coordX[2], coordY[2]);
                            line1.setStroke(c);
                            //System.out.println(p.getChildren().add(line1));
                            pane2.getChildren().addAll(line1);

                        }
                ),


                new KeyFrame(
                        Duration.seconds(4),
                        (ActionEvent actionEvent) -> {
                            Line line1 = new Line(coordX[2], coordY[2], coordX[3], coordY[3]);
                            line1.setStroke(c);
                            //System.out.println(p.getChildren().add(line1));
                            pane2.getChildren().addAll(line1);

                        }
                ),
                new KeyFrame(
                        Duration.seconds(5),
                        (ActionEvent actionEvent) -> {
                            Line line1 = new Line(coordX[3], coordY[3], coordX[4], coordY[4]);
                            line1.setStroke(c);
                            //System.out.println(p.getChildren().add(line1));
                            pane2.getChildren().addAll(line1);
                        }
                )

        );
        timeLine.setCycleCount(1);
        //System.out.println("face si gata");
        timeLine.play();

    }

    //    public int depozit( int masina ){
//        // returnam depozitul din care face parte masina data ca parametru
//        int depozitCurent =0;
//        for(int i=0;i<nrDepots;i++){
//            if(masina>=sum(i) && masina <= sum(i)+ nrCars[i]-1)
//                return i;
//        }
//        return -1;// imposibil sa ajunga aici vreodata speram
//    }
//    public int sum( int depozit){
//        if(depozit ==0)
//            return 0;
//        else return sum(depozit-1) +nrCars[depozit-1];
//    }
    public int depozit(int masina) {
        int sum = 0;
        for (int i = 0; i < nrDepots; i++) {
            if (masina >= sum && masina <= sum + nrCars[i] - 1)
                return i;
            sum = sum + nrCars[i];
        }
        return -1;
    }


}