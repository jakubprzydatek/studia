package obliczenia.instrukcje;

import obliczenia.Liczba;
import obliczenia.Stala;
import obliczenia.Zmienna;

public class InstrukcjaWrite extends Instrukcja{
    private Liczba liczba;
    public InstrukcjaWrite(Liczba liczba){
        this.liczba = liczba;
    }//byc moze write powinna przyjmowac jako argument tylko Stale, ale w poleceniu jest wspomniane o liczbach calkowitych
    @Override
    public void wykonaj() {
        System.out.println(liczba.oblicz());
    }

    @Override
    public String toString() {
        return "write "+liczba.oblicz()+";\r\n";
    }
}
