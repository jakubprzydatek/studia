package com.company;

import com.company.gui.Ramka;
import com.company.model.Pojazd;
import com.company.model.Samochod;
import com.company.model.Tramwaj;
import com.company.utils.FileUtils;

import java.awt.*;

public class Main {

    public static void main(String[] args)
    {
        String objectName = null;
        objectName = sprawdzArgumenty(args, objectName);

        String path = args[0];
        Object pojazd = null;
        pojazd = FileUtils.ReadObjectFromFile(path);
        Samochod auto = new Samochod();

        Object finalPojazd = pojazd;
        String finalObjectName = objectName;
        EventQueue.invokeLater(() -> new Ramka(finalPojazd, finalObjectName, path));

    }

    private static String sprawdzArgumenty(String[] args, String objectName) {
        if(args.length > 1)
        {
            if(args[1].equals(Samochod.class.getSimpleName()) || args[1].equals(Tramwaj.class.getSimpleName()) ||args[1].equals(Pojazd.class.getSimpleName()))
            {
                objectName = args[1];
            }
            else
            {
                System.out.println("Bledna nazwa klasy");
                System.exit(0);
            }
        }
        else if(args.length == 1)
        {
            System.out.println("Nie podano nazwy klasy");
            System.exit(0);
        }
        else
        {
            System.out.println("Nie podano argumentow");
            System.exit(0);
        }
        return objectName;
    }
}
