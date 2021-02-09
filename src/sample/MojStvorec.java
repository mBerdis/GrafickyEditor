package sample;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static sample.Controller.activeCircle;


public class MojStvorec extends MojElement
{
    private double a;
    private Rectangle grafika;

    public MojStvorec(double x, double y, double a, Color farba)
    {
        super(x, y);
        this.a = a;
        grafika = new Rectangle(x, y, a, a);
        grafika.setFill(farba);
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                grafika.setX(e.getX()-a/2);
                grafika.setY(e.getY()-a/2);

            }
        };
//Adding event Filter
        grafika.addEventFilter(MouseEvent.MOUSE_DRAGGED, eventHandler);
        grafika.addEventFilter(MouseEvent.MOUSE_DRAGGED, eventHandler);

        EventHandler<MouseEvent> klik = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (activeCircle != null) activeCircle.setStroke(null);
                activeCircle=grafika;
                activeCircle.setStroke(Color.ORANGE);
                System.out.println("štvorec");
                System.out.println("x: " + (grafika.getX() - a / 2) + " y: " + (grafika.getY() - a / 2));
                System.out.println("a: " + grafika.getWidth() + " color: " + grafika.getFill());
            }
        };
        grafika.addEventFilter(MouseEvent.MOUSE_DRAGGED, klik);
        grafika.addEventFilter(MouseEvent.MOUSE_CLICKED, klik);
    }

    public Rectangle getGrafika()
    {
        return grafika;
    }

    @Override
    public String ulozSa()
    {
        return "S," + grafika.getX() + "," + grafika.getY() + "," + a + "," + grafika.getFill();
    }
}
