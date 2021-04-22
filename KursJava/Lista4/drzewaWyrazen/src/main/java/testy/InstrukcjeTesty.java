package testy;

import obliczenia.Liczba;
import obliczenia.Stala;
import obliczenia.Zmienna;
import obliczenia.instrukcje.*;
import obliczenia.operacje2arg.*;

public class InstrukcjeTesty {
    public static void instrukcjaTest(){
        Instrukcja instrukcja = new InstrukcjaBlokowa(
                new DeklaracjaZmiennej("n"),
                new InstrukcjaRead("n"),
                new InstrukcjaIfElse(new Mniejsze(new Zmienna("n"), new Liczba(2)),
                        new InstrukcjaWrite(new Liczba(0)),
                        new InstrukcjaBlokowa(
                                new DeklaracjaZmiennej("p"),
                                new PrzypiszWartoscZmiennej("p", new Liczba(2)),
                                new DeklaracjaZmiennej("wyn"),
                                new InstrukcjaWhile( new MniejszeRowne(
                                        new Mnozenie(new Zmienna("p"), new Zmienna("p")),
                                                new Zmienna("n")),
                                        new InstrukcjaBlokowa(
                                                new InstrukcjaIf(new Rowne(new Modulo(new Zmienna("n"), new Zmienna("p")),
                                                            new Liczba(0)),
                                                        new InstrukcjaBlokowa(
                                                                new PrzypiszWartoscZmiennej("wyn", new Zmienna("p")),
                                                                new PrzypiszWartoscZmiennej("p", new Zmienna("n"))
                                                        )),
                                                        new PrzypiszWartoscZmiennej("p", new Dodawanie(new Zmienna("p"), new Liczba(1)))
                                        )
                                ),
                                new InstrukcjaIfElse(new Wieksze(new Zmienna("wyn"), new Liczba(0)),
                                        new InstrukcjaWrite(new Liczba(0)), new InstrukcjaWrite(new Liczba(1))
                        ))
        ));
        System.out.println(instrukcja);
        instrukcja.wykonaj();
    }
}
