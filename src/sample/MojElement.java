package sample;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import static sample.Controller.activeCircle;


public class MojElement implements Saveable
{
    private double x;
    private double y;
    protected Shape grafika;
    protected EventHandler<MouseEvent> klik;

    public MojElement(double x, double y)
    {
        this.x = x;
        this.y = y;

        klik = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e)
            {
                if (activeCircle != null) activeCircle.setStroke(null);
                activeCircle = grafika;
                activeCircle.setStroke(Color.BLACK);
            }
        };
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public Shape getGrafika()
    {
        return grafika;
    }

    public void setGrafika(Shape grafika)
    {
        this.grafika = grafika;
    }

    @Override
    public String ulozSa()
    {
        return "" + x + "," + y;
    }
}
