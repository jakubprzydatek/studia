package com.company.testy;

import com.company.InsertException;
import com.company.NoFoundException;
import com.company.struktury.Para;
import com.company.struktury.ZbiorNaTablicyDynamicznej;

public class ZbiorNaTablicyDynamicznejTest {
    public static void testDynamiczneWstawianie()
    {
        Para para = new Para("k", 10);
        Para para1 = new Para("kk", 11);
        Para para2 = new Para("kkk", 12);
        Para para3 = new Para("kkkk", 13);
        Para para4 = new Para("kkkkk", 14);
        Para para5 = new Para("kkkkkk", 15);
        Para para6 = new Para("kkkkkkk", 16);
        Para para7 = new Para("kkkkkkkk", 17);
        ZbiorNaTablicyDynamicznej zbior = new ZbiorNaTablicyDynamicznej();
        try{
            zbior.wstaw(para);
            zbior.wstaw(para1);
            zbior.wstaw(para2);
            zbior.wstaw(para3);
            zbior.wstaw(para4);
            zbior.wstaw(para5);
            zbior.wstaw(para6);
            zbior.wstaw(para7);
        }catch (InsertException e)
        {
            System.out.println("Nie udało się wstawić par");
        }
        System.out.println(zbior.getRozmiar());
    }

    public static void testDynamiczneUsuwanieZmianaRozmiaru()
    {
        Para para = new Para("k", 10);
        Para para1 = new Para("kk", 11);
        Para para2 = new Para("kkk", 12);
        Para para3 = new Para("kkkk", 13);
        Para para4 = new Para("kkkkk", 14);
        Para para5 = new Para("kkkkkk", 15);
        Para para6 = new Para("kkkkkkk", 16);
        Para para7 = new Para("kkkkkkkk", 17);
        ZbiorNaTablicyDynamicznej zbior = new ZbiorNaTablicyDynamicznej();
        try{
            zbior.wstaw(para);
            zbior.wstaw(para1);
            zbior.wstaw(para2);
            zbior.wstaw(para3);
            zbior.wstaw(para4);
            zbior.wstaw(para5);
            zbior.wstaw(para6);
            zbior.wstaw(para7);
        }catch (InsertException e)
        {
            System.out.println("Nie udało się wstawić par");
        }
        System.out.println("Ilosc par w zbiorze: " + zbior.ile());
        zbior.usun("k");
        zbior.usun("kk");
        zbior.usun("kkk");
        zbior.usun("kkkk");
        System.out.println("Rozmiar zbioru po usunieciu 4 elementow: "+zbior.getRozmiar());
        zbior.usun("kkkkkk");
        zbior.usun("kkkkk");
        System.out.println("Rozmiar zbioru po usunieciu kolejnych 2 elementow: "+zbior.getRozmiar());

    }
}
