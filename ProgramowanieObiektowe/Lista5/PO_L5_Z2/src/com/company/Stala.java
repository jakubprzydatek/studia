package com.company;

public class Stala implements Argument{

    private int value;
    public Stala(int value)
    {
        this.value = value;
    }

    @Override
    public int oblicz() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(oblicz());
    }
}
