package com.company;

public class Zmienna implements Argument {

    private int value;
    public Zmienna(String value)
    {
        this.value = value.hashCode();
    }

    @Override
    public int oblicz() {

        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
