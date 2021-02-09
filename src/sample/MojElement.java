package sample;

public class MojElement implements Saveable
{
    private double x;
    private double y;

    public MojElement(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public String ulozSa()
    {
        return null/*"" + x + "," + y*/;
    }
}
