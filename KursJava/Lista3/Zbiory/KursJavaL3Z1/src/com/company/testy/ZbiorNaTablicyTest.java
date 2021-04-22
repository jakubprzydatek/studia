package com.company.testy;

import com.company.InsertException;
import com.company.NoFoundException;
import com.company.struktury.Para;
import com.company.struktury.ZbiorNaTablicy;

public class ZbiorNaTablicyTest {
    public static void testInsertExistedKey()
    {
        Para para = new Para("klucz", 10);
        Para para1 = new Para("klucz", 15);
        ZbiorNaTablicy zbior = new ZbiorNaTablicy(2);

        try{
            zbior.wstaw(para);
            zbior.wstaw(para1);
        }catch (InsertException e)
        {
            System.out.println("Nie można wstawić par o tym samym kluczu");
        }
    }

    public static void testSzukaj()
    {
        Para para = new Para("klucz", 10);
        Para para1 = new Para("klucztest", 15);
        ZbiorNaTablicy zbior = new ZbiorNaTablicy(2);
        try{
            zbior.wstaw(para);
            zbior.wstaw(para1);
        }catch (InsertException e)
        {
            System.out.println("nie udało wstawić się par");
        }
        try{
            zbior.szukaj("klucztest");
        }catch (NoFoundException e)
        {
            System.out.println("Nie znaleziono pary o podanym kluczu");
        }
    }


    public static void testUsunCzytaj()
    {
        Para para = new Para("klucz", 10);
        Para para1 = new Para("klucztest", 15);
        ZbiorNaTablicy zbior = new ZbiorNaTablicy(2);
        try{
            zbior.wstaw(para);
            zbior.wstaw(para1);
        }catch (InsertException e)
        {
            System.out.println("nie udało wstawić się par");
        }
        double valueToRead = 0;
        zbior.usun("klucz");
        try{
            valueToRead = zbior.czytaj("klucz");
        }catch (NoFoundException e)
        {
            System.out.println("Nie udało się przeczytać wartości. Para o podanym kluczu została usunięta");
        }
        try{
            valueToRead = zbior.czytaj("klucztest");
        }catch (NoFoundException e)
        {
            System.out.println("Nie udało się przeczytać wartości. Para o podanym kluczu została usunięta");
        }
        System.out.println("Przeczytana wartość: " + valueToRead);
    }

    public static void testUstaw()
    {
        Para para = new Para("klucz", 10);
        Para para1 = new Para("klucztest", 15);
        ZbiorNaTablicy zbior = new ZbiorNaTablicy(2);
        try{
            zbior.wstaw(para);
            zbior.wstaw(para1);
        }catch (InsertException e)
        {
            System.out.println("nie udało wstawić się par");
        }
        try{
            System.out.println("Wartosc klucza klucz przed ustawieniem: " + zbior.czytaj("klucz"));
        }catch (NoFoundException e)
        {
            System.out.println("Nie udało się przeczytać wartości. Para o podanym kluczu nie istnieje");
        }
        Para para2 = new Para("klucz", 234);
        try{
            zbior.ustaw(para2);
        }catch (InsertException e)
        {
            System.out.println("Nie udało się wstawic pary o podanym kluczu");
        }

        try{
            System.out.println("Wartosc klucza klucz po ustawieniu: " + zbior.czytaj("klucz"));
        }catch (NoFoundException e)
        {
            System.out.println("Nie udało się przeczytać wartości. Para o podanym kluczu nie istnieje");
        }
    }

    public static void testCzyscIle()
    {
        Para para = new Para("klucz", 10);
        Para para1 = new Para("klucztest", 15);
        ZbiorNaTablicy zbior = new ZbiorNaTablicy(2);
        try{
            zbior.wstaw(para);
            zbior.wstaw(para1);
        }catch (InsertException e)
        {
            System.out.println("nie udało wstawić się par");
        }
        System.out.println(zbior.ile());
        zbior.czysc();
        System.out.println(zbior.ile());
    }
}
