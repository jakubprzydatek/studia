package com.company;

public abstract class Wyrazenie implements Argument {

    protected Argument argument1;
    protected Argument argument2;
    protected char znak;



    @Override
    public String toString() {
        return "("+argument1 + znak + argument2+")";
    }

}
