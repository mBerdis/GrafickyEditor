package sample;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static sample.Controller.activeCircle;


public class MojStvorec extends MojElement
{
    private double a;

    public MojStvorec(double x, double y, double aA, Color farba)
    {
        super(x, y);
        this.a = aA;
        grafika = new Rectangle(x, y, a, a);
        grafika.setFill(farba);
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e)
            {
                Rectangle grf = (Rectangle) grafika;
                if (grafika.getCursor() == Cursor.DEFAULT)
                {
                    setX(e.getX());
                    grf.setX(e.getX() - a / 2);
                    setY(e.getY());
                    grf.setY(e.getY() - a / 2);
                }
                else
                {
                    a = Math.max(Math.abs(e.getX() - (getX() + a / 2)), Math.abs(e.getY() - (getY() + a / 2)));
                    ((Rectangle) grafika).setHeight(a * 2);
                    ((Rectangle) grafika).setWidth(a * 2);
                }

            }
        };

        EventHandler<MouseEvent> cursorChangeHandler = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e)
            {
                if (e.getX() - getX() < 5 || (getX() + a) - e.getX() < 5)
                {
                    grafika.setCursor(Cursor.H_RESIZE);
                }
                else if (e.getY() - getY() < 5 || (getY() + a) - e.getY() < 5)
                    grafika.setCursor(Cursor.V_RESIZE);
                else grafika.setCursor(Cursor.DEFAULT);

                /*if (a / 2 - Math.abs(e.getX() - (getX() + a / 2)) < 5)
                {
                    grafika.setCursor(Cursor.H_RESIZE);
                }
                else if (a / 2 - Math.abs(e.getY() - (getY() + a / 2)) < 5)
                    grafika.setCursor(Cursor.V_RESIZE);
                else grafika.setCursor(Cursor.DEFAULT);*/

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
        return "S," + super.ulozSa() + "," + a + "," + grafika.getFill();
    }
}
