package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MojKruh extends MojElement
{
    private double polomer;
    private Circle grafika;

    public MojKruh(double x, double y, double polomer, Color farba)
    {
        super(x, y);
        this.polomer = polomer;
        grafika = new Circle(x, y, polomer, farba);
    }

    public Circle getGrafika()
    {
        return grafika;
    }

    @Override
    public String ulozSa()
    {
        return "K," + super.ulozSa() + "," + polomer + "," + grafika.getFill();
    }
}
