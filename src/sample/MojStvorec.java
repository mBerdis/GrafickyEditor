package sample;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


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
                    a = distBetweenPointAndLine(((Rectangle) grafika).getX(), ((Rectangle) grafika).getY(), ((Rectangle) grafika).getX() + a, ((Rectangle) grafika).getY() + a, e.getX(), e.getY());

                    ((Rectangle) grafika).setHeight(a);
                    ((Rectangle) grafika).setWidth(a);
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


    private static double distBetweenPointAndLine(double x, double y, double x1, double y1, double x2, double y2)
    {
        // A - the standalone point (x, y)
        // B - start point of the line segment (x1, y1)
        // C - end point of the line segment (x2, y2)
        // D - the crossing point between line from A to BC

        double AB = distBetween(x, y, x1, y1);
        double BC = distBetween(x1, y1, x2, y2);
        double AC = distBetween(x, y, x2, y2);

        // Heron's formula
        double s = (AB + BC + AC) / 2;
        double area = (double) Math.sqrt(s * (s - AB) * (s - BC) * (s - AC));

        // but also area == (BC * AD) / 2
        // BC * AD == 2 * area
        // AD == (2 * area) / BC
        // TODO: check if BC == 0
        double AD = (2 * area) / BC;
        if (AD < 10) return 10;
        else return AD;
    }

    private static double distBetween(double x, double y, double x1, double y1)
    {
        double xx = x1 - x;
        double yy = y1 - y;

        return (double) Math.sqrt(xx * xx + yy * yy);
    }
}
