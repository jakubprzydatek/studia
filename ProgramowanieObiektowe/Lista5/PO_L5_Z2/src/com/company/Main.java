package com.company;

public class Main {

    public static void main(String[] args) {
        Wyrazenie wyrazenie = new Odejmowanie(new Dodaj(new Stala(5), new Zmienna("x")), new Dzielenie(new Mnozenie(new Stala(10), new Stala(5)), new Stala(1)));
        System.out.println(wyrazenie.toString());
        System.out.println(wyrazenie.oblicz());

    }
}
