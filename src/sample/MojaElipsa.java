package sample;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

import static sample.Controller.activeCircle;

public class MojaElipsa extends MojElement
{
    private double radiusX;
    private double radiusY;

    public MojaElipsa(double x, double y, double aRadiusX, double aRadiusY, Color farba)
    {
        super(x, y);
        this.radiusX = aRadiusX;
        this.radiusY = aRadiusY;
        grafika = new Ellipse(x, y, radiusX, radiusY);
        grafika.setFill(farba);
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e)
            {
                Ellipse grf = (Ellipse) grafika;
                if (grafika.getCursor() == Cursor.DEFAULT)
                {
                    setX(e.getX());
                    grf.setCenterX(e.getX());
                    setY(e.getY());
                    grf.setCenterY(e.getY());
                }
                else
                {
                    if (Math.abs(e.getX() - getX()) > Math.abs(e.getY() - getY()))
                    {
                        radiusX = Math.abs(e.getX() - getX());
                        grf.setRadiusX(radiusX);
                    }
                    else
                    {
                        radiusY = Math.abs(e.getY() - getY());
                        grf.setRadiusY(radiusY);
                    }
                }
            }
        };

        EventHandler<MouseEvent> cursorChangeHandler = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e)
            {
                System.out.println("Polomer: " + radiusX);
                System.out.println("E: " + e.getX());
                System.out.println("Circle: " + getX());
                System.out.println("Math: " + (e.getX() - getX()));
                System.out.println(radiusX - Math.abs(e.getX() - getX()));
                if (radiusX - Math.abs(e.getX() - getX()) < 5)
                    grafika.setCursor(Cursor.H_RESIZE);
                else if (radiusY - Math.abs(e.getY() - getY()) < 5)
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
        return "E," + super.ulozSa() + "," + radiusX + "," + radiusY + "," + grafika.getFill();
    }
}
