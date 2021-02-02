package sample;


import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Controller
{
    Random rn = new Random();
    public AnchorPane platno;
    ArrayList<MojElement> obraz = new ArrayList<>();

    public void newCircle(ActionEvent actionEvent)
    {
        Color farba = Color.rgb(rn.nextInt(256), rn.nextInt(256), rn.nextInt(256));
        MojKruh kruh = new MojKruh(rn.nextInt(500), rn.nextInt(500), rn.nextInt(30) + 10, farba);
        obraz.add(kruh);
        platno.getChildren().add(kruh.getGrafika());
    }

    public void newSquare(ActionEvent actionEvent)
    {
        Color farba = Color.rgb(rn.nextInt(256), rn.nextInt(256), rn.nextInt(256));
        MojStvorec stvorec = new MojStvorec(rn.nextInt(500), rn.nextInt(500), rn.nextInt(30) + 10, farba);
        obraz.add(stvorec);
        platno.getChildren().add(stvorec.getGrafika());
    }

    public void newEllipse(ActionEvent actionEvent)
    {
        Color farba = Color.rgb(rn.nextInt(256), rn.nextInt(256), rn.nextInt(256));
        MojaElipsa elipsa = new MojaElipsa(rn.nextInt(500), rn.nextInt(500), rn.nextInt(30) + 10, rn.nextInt(30) + 10, farba);
        obraz.add(elipsa);
        platno.getChildren().add(elipsa.getGrafika());
    }

    public void save(ActionEvent actionEvent) throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter("obraz.txt"));
        for (MojElement element : obraz)
        {
            bw.write(element.ulozSa());
            bw.newLine();
        }
        bw.close();
    }

    public void load(ActionEvent actionEvent)
    {
    }
}
