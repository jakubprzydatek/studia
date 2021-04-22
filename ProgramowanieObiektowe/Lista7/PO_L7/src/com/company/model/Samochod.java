package com.company.model;

import com.company.model.Pojazd;

public class Samochod extends Pojazd {

    private String rodzajPaliwa = "-";
    private int iloscKoniMechanicznych = 0;
    private int pojemnoscBagaznika = 0;


    public String getRodzajPaliwa() {
        return rodzajPaliwa;
    }

    public void setRodzajPaliwa(String rodzajPaliwa) {
        this.rodzajPaliwa = rodzajPaliwa;
    }

    public int getIloscKoniMechanicznych() {
        return iloscKoniMechanicznych;
    }

    public void setIloscKoniMechanicznych(int iloscKoniMechanicznych) {
        this.iloscKoniMechanicznych = iloscKoniMechanicznych;
    }

    public int getPojemnoscBagaznika() {
        return pojemnoscBagaznika;
    }

    public void setPojemnoscBagaznika(int pojemnoscBagaznika) {
        this.pojemnoscBagaznika = pojemnoscBagaznika;
    }

    @Override
    public String toString() {
        return "--- Wlasciwosci obiektu Samochod --- " + System.getProperty("line.separator") +
                "Rodzaj paliwa: " + rodzajPaliwa + System.getProperty("line.separator") +
                "Ilosc koni mechanicznych: " + iloscKoniMechanicznych + System.getProperty("line.separator") +
                "Pojemnosc bagaznika: " + pojemnoscBagaznika + System.getProperty("line.separator") +
                "Typ: " + typ + System.getProperty("line.separator") +
                "Liczba miejsc: " + liczbaMiejsc + System.getProperty("line.separator") +
                "Kategoria prawa jazdy: " + kategoriaPrawaJazdy;
    }

    public void setDefault()
    {
        this.setTyp("-");
        this.setLiczbaMiejsc(0);
        this.setKategoriaPrawaJazdy("-");
        this.setPojemnoscBagaznika(0);
        this.setIloscKoniMechanicznych(0);
        this.setRodzajPaliwa("-");
    }
}
