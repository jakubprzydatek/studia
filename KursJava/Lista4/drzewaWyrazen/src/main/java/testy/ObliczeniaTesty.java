package testy;


import obliczenia.Liczba;
import obliczenia.Wyrazenie;
import obliczenia.Zmienna;
import obliczenia.operacje1arg.ZmianaZnaku;
import obliczenia.operacje2arg.*;

public class ObliczeniaTesty {



    public static void testWyr1(){
        Wyrazenie wyr = new Dodawanie(new Liczba(3), new Liczba(5));
        System.out.println(wyr.toString());
        System.out.println(wyr.oblicz());
    }

    public static void testWyr2(){
        Zmienna.zbiorZmiennych.put("x", 2);
        Wyrazenie wyr = new Mnozenie(
                            new ZmianaZnaku(new Odejmowanie(new Liczba(2), new Zmienna("x"))),
                            new Liczba(7));
        System.out.println(wyr.toString());
        System.out.println(wyr.oblicz());
    }

    public static void testWyr3(){
        Wyrazenie wyr = new Dzielenie
                (new Odejmowanie
                        (new Mnozenie(new Liczba(3), new Liczba(11)
                        ),new Liczba(1)),
                new Dodawanie(new Liczba(7), new Liczba(5)));
        System.out.println(wyr.toString());
        System.out.println(wyr.oblicz());
    }
    public static void testWyr4(){
        Zmienna.zbiorZmiennych.put("x", 2);
        Wyrazenie wyr = new Minimum(
                new Mnozenie(new Dodawanie(new Zmienna("x"), new Liczba(13)),
                            new Zmienna("x")),
                new Modulo(new Odejmowanie(new Liczba(1), new Zmienna("x")),
                            new Liczba(2)));
        System.out.println(wyr.toString());
        System.out.println(wyr.oblicz());
    }

    public static void testWyr5(){
        Zmienna.zbiorZmiennych.put("x", 2);
        Zmienna.zbiorZmiennych.put("y", 16);
        Wyrazenie wyr = new Mniejsze(new Dodawanie(new Potegowanie(new Liczba(2), new Liczba(5)),
                                                    new Mnozenie(new Zmienna("x"),
                                                            new LogarytmDyskretny(new Liczba(2), new Zmienna("y")))), new Liczba(20));
        System.out.println(wyr.toString());
        System.out.println(wyr.oblicz());
    }
}
