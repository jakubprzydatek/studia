package com.company;

public class Dzielenie extends Wyrazenie {


    public Dzielenie(Argument argument1, Argument argument2)
    {
        this.znak = '/';
        this.argument1 = argument1;
        this.argument2 = argument2;
    }
    @Override
    public int oblicz() {
        if(argument2.oblicz() == 0)
        {
            System.out.println("Dzielnie przez zero!");
            return 0;
        }
        return argument1.oblicz()/argument2.oblicz();
    }
}
