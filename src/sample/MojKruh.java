package sample;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseEvent;



import static sample.Controller.activeCircle;

public class MojKruh extends MojElement
{
    private double polomer;

    public MojKruh(double x, double y, double aPolomer, Color farba)
    {
        super(x, y);
        this.polomer = aPolomer;
        grafika = new Circle(x, y, polomer, farba);

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e)
            {
                Circle grf = (Circle) grafika;
                if (grafika.getCursor() == Cursor.DEFAULT)
                {
                    setX(e.getX());
                    grf.setCenterX(e.getX());
                    setY(e.getY());
                    grf.setCenterY(e.getY());
                }
                else
                {
                    polomer = Math.max(Math.abs(e.getX() - getX()), Math.abs(e.getY() - getY()));
                    grf.setRadius(polomer);
                }
            }
        };

        EventHandler<MouseEvent> cursorChangeHandler = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e)
            {
                if (polomer - Math.abs(e.getX() - getX()) < 5)
                    grafika.setCursor(Cursor.H_RESIZE);
                else if (polomer - Math.abs(e.getY() - getY()) < 5)
                    grafika.setCursor(Cursor.V_RESIZE);
                else grafika.setCursor(Cursor.DEFAULT);

            }
        };
        grafika.addEventFilter(MouseEvent.MOUSE_DRAGGED, eventHandler);
        grafika.addEventFilter(MouseEvent.MOUSE_DRAGGED, klik);
        grafika.addEventFilter(MouseEvent.MOUSE_CLICKED, klik);
        grafika.addEventFilter(MouseEvent.MOUSE_MOVED, cursorChangeHandler);
    }


    @Override
    public String ulozSa()
    {
        return "K," + super.ulozSa() + "," + polomer + "," + grafika.getFill();
    }
}
