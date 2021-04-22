package com.company.model;

import java.io.Serializable;

public class Pojazd implements Serializable {
    protected String typ = "-";
    protected int liczbaMiejsc = 0;
    protected String kategoriaPrawaJazdy = "-";

    public Pojazd() {
    }

    @Override
    public String toString() {
        return "--- Wlasciwosci obiektu Pojazd --- " + System.getProperty("line.separator") +
                "Typ: " + typ + System.getProperty("line.separator") +
                "Liczba miejsc: " + liczbaMiejsc + System.getProperty("line.separator") +
                "Kategoria prawa jazdy: " + kategoriaPrawaJazdy;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public int getLiczbaMiejsc() {
        return liczbaMiejsc;
    }

    public void setLiczbaMiejsc(int liczbaMiejsc) {
        this.liczbaMiejsc = liczbaMiejsc;
    }

    public String getKategoriaPrawaJazdy() {
        return kategoriaPrawaJazdy;
    }

    public void setKategoriaPrawaJazdy(String kategoriaPrawaJazdy) {
        this.kategoriaPrawaJazdy = kategoriaPrawaJazdy;
    }
}
