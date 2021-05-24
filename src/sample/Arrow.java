package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/**
 * @author kn
 */
public class Arrow extends Path {
    private static final double defaultArrowHeadSize = 5.0;

    public Arrow(double startX, double startY, double endX, double endY, double arrowHeadSize,Color c) {
        super();
        strokeProperty().bind(fillProperty());
        setFill(c);

        //Line
//        getElements().add(new MoveTo(startX, startY));
//        getElements().add(new LineTo(endX, endY));

        //ArrowHead
        // arctangenta de o valoare da un unghi in grade/ radiani
        // din gradele obtinute cu arctangenta scadem pi/2 adica 90 de grade si obtinem un unghi
        double angle = Math.atan2((endY - startY), (endX - startX)) - Math.PI / 2.0;
        // calculam sinusul si cosinusul acelui unghi

        double sin = Math.sin(angle); // sin ( ceva -90 ) = -cos *ceva      unde ceva = arcatangenta
        ///
        double cos = Math.cos(angle); // cosa cosb + sina sin b   = > sin ceva
        //point1
        // a= -30 de grade = -pi/6
        // b =angle cu minus
        // sin ( a-b) =sina * cosB +/- cosA*sinB

        double x1 = (-1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + endX;
        double y1 = (-1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + endY;
        //point2

        // sin(a-b) sau sin(a-b)
        double x2 = (1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + endX;
        double y2 = (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + endY;
        getElements().add(new MoveTo(endX, endY));
        getElements().add(new LineTo(x1, y1));
        getElements().add(new LineTo(x2, y2));
        getElements().add(new LineTo(endX, endY));
    }

    public Arrow(double startX, double startY, double endX, double endY) {
        this(startX, startY, endX, endY, defaultArrowHeadSize,Color.LIGHTBLUE);
    }
}