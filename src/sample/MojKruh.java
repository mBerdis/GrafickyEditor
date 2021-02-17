package sample;

import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseEvent;


import java.awt.*;

import static sample.Controller.activeCircle;


public class MojKruh extends MojElement
{

    private double polomer;
    private Circle grafika;

    public MojKruh(double x, double y, double polomer, Color farba)
    {
        super(x, y);
        this.polomer = polomer;
        grafika = new Circle(x, y, polomer, farba);
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                grafika.setCenterX(e.getX());
                grafika.setCenterY(e.getY());


            }
        };
//Adding event Filter
        grafika.addEventFilter(MouseEvent.MOUSE_DRAGGED, eventHandler);

        EventHandler<MouseEvent> klik = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (activeCircle != null) activeCircle.setStroke(null);
                activeCircle=grafika;
                activeCircle.setStroke(Color.ORANGE);

                System.out.println("kruh");
                System.out.println("x: " + grafika.getCenterX() + " y: " + grafika.getCenterY());
                System.out.println("r: " + grafika.getRadius() + " color: " + grafika.getFill());
            }
        };
        grafika.addEventFilter(MouseEvent.MOUSE_DRAGGED, klik);
        grafika.addEventFilter(MouseEvent.MOUSE_CLICKED, klik);
    }

    public Circle getGrafika()
    {
        return grafika;
    }

    @Override
    public String ulozSa()
    {
        return "K," + grafika.getCenterX() + "," + grafika.getCenterY() + "," + polomer + "," + grafika.getFill();
    }
}
