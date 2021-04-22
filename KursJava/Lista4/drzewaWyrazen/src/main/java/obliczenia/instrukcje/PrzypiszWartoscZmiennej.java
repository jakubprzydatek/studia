package obliczenia.instrukcje;

import obliczenia.Wyrazenie;
import obliczenia.Zmienna;

public class PrzypiszWartoscZmiennej extends Instrukcja{


    private String nazwaZmiennej;
    private Wyrazenie wartoscZmiennej;
    public PrzypiszWartoscZmiennej(String nazwaZmiennej, Wyrazenie wartosc){
        if(nazwaZmiennej == null || wartosc == null) throw new NullPointerException();
        this.nazwaZmiennej = nazwaZmiennej;
        this.wartoscZmiennej= wartosc;
    }
    @Override
    public void wykonaj() {
        Integer integer = wartoscZmiennej.oblicz();
        if(Zmienna.zbiorZmiennych.containsKey(nazwaZmiennej)){
            Zmienna.zbiorZmiennych.put(nazwaZmiennej, integer);
        }else{
            System.out.println("Zmienna nie została zadeklarowana");
        }
    }


    @Override
    public String toString() {
        return nazwaZmiennej +" = "+wartoscZmiennej.toString()+";\r\n";
    }
}
