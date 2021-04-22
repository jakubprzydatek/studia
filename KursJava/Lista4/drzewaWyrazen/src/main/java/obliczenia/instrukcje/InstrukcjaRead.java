package obliczenia.instrukcje;

import obliczenia.Liczba;
import obliczenia.Zmienna;

import java.lang.reflect.Executable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InstrukcjaRead extends Instrukcja{
    private String nazwaZmiennej;
    public InstrukcjaRead(String zmienna){
        if(zmienna == null) throw new NullPointerException();
        this.nazwaZmiennej = zmienna;
    }
    @Override
    public void wykonaj() {
        boolean reading = true;
        new Scanner(System.in);
        Scanner scan;
        int wartoscZmiennej = 0;
        System.out.println("Czytanie z wejścia: ");
        while (reading){
            reading = false;
            try{
                scan = new Scanner(System.in);
                wartoscZmiennej = scan.nextInt();
            }catch (InputMismatchException e){
                reading = true;
                System.out.println("Mozna wczytac tylko liczby calkowite");
            }
        }

        Instrukcja przypisz = new PrzypiszWartoscZmiennej(nazwaZmiennej, new Liczba(wartoscZmiennej));
        przypisz.wykonaj();
    }

    @Override
    public String toString() {
        return "read "+nazwaZmiennej+";\r\n";
    }
}
