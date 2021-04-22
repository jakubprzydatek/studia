package com.company;

public class Mnozenie extends Wyrazenie {

    public Mnozenie(Argument argument1, Argument argument2)
    {
        this.znak = '*';
        this.argument1 = argument1;
        this.argument2 = argument2;
    }

    @Override
    public int oblicz() {
        return argument1.oblicz()*argument2.oblicz();
    }
}
