package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


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
    }

    public Rectangle getGrafika()
    {
        return grafika;
    }

    @Override
    public String ulozSa()
    {
        return "S," + super.ulozSa() + "," + a + "," + grafika.getFill();
    }
}
