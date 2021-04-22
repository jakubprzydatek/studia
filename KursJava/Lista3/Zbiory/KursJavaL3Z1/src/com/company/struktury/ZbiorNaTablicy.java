package com.company.struktury;

import com.company.InsertException;
import com.company.MyException;
import com.company.NoFoundException;

public class ZbiorNaTablicy extends Zbior{

    protected Para[] pary;
    protected int rozmiar;
    protected int iloscPar;

    public ZbiorNaTablicy(int rozmiar)
    {
        if(rozmiar < 2) throw new IllegalArgumentException("Rozmiar musi być większy od 2");
        this.rozmiar = rozmiar;
        this.iloscPar = 0;
        pary = new Para[rozmiar];
    }

    @Override
    public Para szukaj(String k) throws NoFoundException {
        for(int i = 0; i < this.iloscPar; i++)
        {
            if(pary[i].klucz.equals(k))
            {
                return pary[i];
            }
        }
        throw new NoFoundException("Nie znaleziono pary o zadanym kluczu");
    }

    @Override
    public void wstaw(Para p) throws InsertException {
        if(iloscPar == rozmiar)
        {
            throw new InsertException("Zbior nie może przechować więcej par");
        }else{
            try{
                this.szukaj(p.klucz);
            }
            catch (NoFoundException e){
                pary[iloscPar] = p;
                iloscPar += 1;
                return;
            }
        }
        throw new InsertException("Zbior posiada już parę o takim kluczu");

    }

    @Override
    public void usun(String k) {
        Para paraToRemove = null;
        boolean found = true;
        boolean removed = false;
        try{
            paraToRemove = this.szukaj(k);
        }
        catch (NoFoundException e){
            found = false;
        }
        int j;
        if(found){
            this.iloscPar -= 1;
            for(int i=0;i<this.iloscPar;i++)
            {
                if(removed)
                {
                    this.pary[i] = this.pary[i+1];
                }
                if(this.pary[i].equals(paraToRemove))
                {
                    removed = true;
                    this.pary[i] = this.pary[i+1];
                }
            }
        }

    }

    @Override
    public double czytaj(String k) throws NoFoundException {
        return this.szukaj(k).getWartosc();
    }

    @Override
    public void ustaw(Para p) throws InsertException {
        Para wyszukanaPara = null;
        boolean found = true;
        try{
            wyszukanaPara = this.szukaj(p.klucz);
        }catch (NoFoundException e){
            this.wstaw(p);
            found = false;
        }
        if(found)
        {
            for(int i=0;i<this.iloscPar;i++)
            {
                if(this.pary[i].equals(wyszukanaPara))
                {
                    this.pary[i].setWartosc(p.getWartosc());
                }
            }
        }

    }

    @Override
    public void czysc() {
        this.iloscPar = 0;
    }

    @Override
    public int ile() {
        return this.iloscPar;
    }

}
