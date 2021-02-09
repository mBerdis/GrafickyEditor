package sample;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import static sample.Controller.activeCircle;

public class MojaElipsa extends MojElement
{
    private double radiusX;
    private double radiusY;
    private Ellipse grafika;

    public MojaElipsa(double x, double y, double radiusX, double radiusY, Color farba)
    {
        super(x, y);
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        grafika = new Ellipse(x, y, radiusX, radiusY);
        grafika.setFill(farba);
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                grafika.setCenterX(e.getX());
                grafika.setCenterY(e.getY());

            }
        };
//Adding event Filter
        grafika.addEventFilter(MouseEvent.MOUSE_DRAGGED, eventHandler);
        grafika.addEventFilter(MouseEvent.MOUSE_DRAGGED, eventHandler);

        EventHandler<MouseEvent> klik = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (activeCircle!=null)activeCircle.setStroke(Color.WHITE);
                activeCircle=grafika;
                activeCircle.setStroke(Color.BLACK);
            }
        };
        grafika.addEventFilter(MouseEvent.MOUSE_DRAGGED, klik);
        grafika.addEventFilter(MouseEvent.MOUSE_CLICKED, klik);
    }

    public Ellipse getGrafika()
    {
        return grafika;
    }

    @Override
    public String ulozSa()
    {
        return "E," + grafika.getCenterX() + "," + grafika.getCenterY() + "," + radiusX + "," + radiusY + "," + grafika.getFill();
    }
}
