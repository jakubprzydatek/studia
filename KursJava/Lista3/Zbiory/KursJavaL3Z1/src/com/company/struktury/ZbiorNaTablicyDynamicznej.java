package com.company.struktury;

import com.company.InsertException;
import com.company.NoFoundException;

public class ZbiorNaTablicyDynamicznej extends ZbiorNaTablicy {


    public ZbiorNaTablicyDynamicznej() {
        super(2);
    }

    @Override
    public void wstaw(Para p) throws InsertException {
        if (iloscPar == rozmiar) {
            Para[] tmpPary = new Para[rozmiar * 2];
            rozmiar*=2;
            tmpPary = copyToGraterArray(pary, tmpPary);
            pary = tmpPary;
        }
        try {
            this.szukaj(p.klucz);
        } catch (NoFoundException e) {
            pary[iloscPar] = p;
            iloscPar += 1;
            return;
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
        if(iloscPar*4 == rozmiar)
        {
            Para[] tmpPary = new Para[rozmiar/4];
            rozmiar/=4;
            tmpPary = copyToSmallerArray(pary, tmpPary, iloscPar);
            pary = tmpPary;
        }
    }


    public static Para[] copyToGraterArray(Para[] src, Para[] target)
        {
            for(int i=0;i<src.length;i++)
            {
                target[i] = src[i];
            }
            return target;
        }


    public static Para[] copyToSmallerArray(Para[] src, Para[] target, int sizeOfTarget)
    {
        for(int i=0;i<sizeOfTarget;i++)
        {
            target[i] = src[i];
        }
        return target;
    }

    public int getRozmiar()
    {
        return this.rozmiar;
    }
}

