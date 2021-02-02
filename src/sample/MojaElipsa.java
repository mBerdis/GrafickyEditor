package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

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
    }

    public Ellipse getGrafika()
    {
        return grafika;
    }

    @Override
    public String ulozSa()
    {
        return "E," + super.ulozSa() + "," + radiusX + "," + radiusY + "," + grafika.getFill();
    }
}
