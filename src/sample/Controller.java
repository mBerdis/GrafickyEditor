package sample;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Controller
{
    Random rn = new Random();
    public AnchorPane platno;
    ArrayList<MojElement> obraz = new ArrayList<>();
    static Shape activeCircle=null;

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
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("obraz.txt"));
            String row = br.readLine();


            while (row != null)
            {
                String[] data = row.split(",");

                switch (data[0])
                {
                    case "E": MojaElipsa elipsa = new MojaElipsa(Double.parseDouble(data[1]), Double.parseDouble(data[2]),
                            Double.parseDouble(data[3]),Double.parseDouble(data[4]), Color.valueOf(data[5]));
                            obraz.add(elipsa);
                            platno.getChildren().add(elipsa.getGrafika());
                            break;

                    case "K": MojKruh kruh = new MojKruh(Double.parseDouble(data[1]), Double.parseDouble(data[2]),
                            Double.parseDouble(data[3]),Color.valueOf(data[4]));
                            obraz.add(kruh);
                            platno.getChildren().add(kruh.getGrafika());
                            break;

                    case "S": MojStvorec stvorec = new MojStvorec(Double.parseDouble(data[1]), Double.parseDouble(data[2]),
                            Double.parseDouble(data[3]), Color.valueOf(data[4]));
                            obraz.add(stvorec);
                            platno.getChildren().add(stvorec.getGrafika());
                            break;

                    default: obraz.add(new MojElement(Double.parseDouble(data[1]), Double.parseDouble(data[2])));
                }

                row = br.readLine();
            }

            br.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.err.println("Problem with loading from file.");
        }
    }

    public void clear(ActionEvent actionEvent)
    {
        obraz.clear();
        platno.getChildren().clear();
    }


}
