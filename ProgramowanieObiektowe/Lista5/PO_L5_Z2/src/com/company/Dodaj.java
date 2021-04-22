package com.company;

public class Dodaj extends Wyrazenie {

    public Dodaj(Argument arg1, Argument arg2)
    {
        this.znak = '+';
        this.argument1 = arg1;
        this.argument2 = arg2;
    }


    @Override
    public int oblicz() {
        return argument1.oblicz()+argument2.oblicz();
    }

}
