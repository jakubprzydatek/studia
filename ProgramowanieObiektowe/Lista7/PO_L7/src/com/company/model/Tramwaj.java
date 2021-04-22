package com.company.model;

import com.company.model.Pojazd;

public class Tramwaj extends Pojazd {

    private boolean czyNiskopodlogowy = false;
    private boolean czyMiejscaDlaNiepelnosprawnych = false;
    private int szerokoscOsi = 0;

    public boolean isCzyNiskopodlogowy() {
        return czyNiskopodlogowy;
    }

    public void setCzyNiskopodlogowy(boolean czyNiskopodlogowy) {
        this.czyNiskopodlogowy = czyNiskopodlogowy;
    }

    public boolean isCzyMiejscaDlaNiepelnosprawnych() {
        return czyMiejscaDlaNiepelnosprawnych;
    }

    public void setCzyMiejscaDlaNiepelnosprawnych(boolean czyMiejscaDlaNiepelnosprawnych) {
        this.czyMiejscaDlaNiepelnosprawnych = czyMiejscaDlaNiepelnosprawnych;
    }

    public int getSzerokoscOsi() {
        return szerokoscOsi;
    }

    public void setSzerokoscOsi(int szerokoscOsi) {
        this.szerokoscOsi = szerokoscOsi;
    }

    @Override
    public String toString() {
        return "--- Wlasciwosci obiektu Tramwaj --- " + System.getProperty("line.separator") +
                "Czy niskopodlogowy: " + czyNiskopodlogowy + System.getProperty("line.separator") +
                "Czy miejsca dla niepelnosprawnych: " + czyMiejscaDlaNiepelnosprawnych + System.getProperty("line.separator") +
                "Szerokosc osi: " + szerokoscOsi + System.getProperty("line.separator") +
                "Typ: " + typ + System.getProperty("line.separator") +
                "Liczba miejsc: " + liczbaMiejsc + System.getProperty("line.separator") +
                "Kategoria prawa jazdy: " + kategoriaPrawaJazdy;
    }

}
