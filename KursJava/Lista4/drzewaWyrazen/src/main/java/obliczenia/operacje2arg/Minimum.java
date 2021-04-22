package obliczenia.operacje2arg;

import obliczenia.Wyrazenie;

public class Minimum extends TwoArgOperation{
    public Minimum(Wyrazenie arg1, Wyrazenie arg2) {
        super(arg1, arg2, "min");
    }

    @Override
    public int oblicz() {
        if(arg1.oblicz() < arg2.oblicz()){
            return arg1.oblicz();
        }
        else{
            return arg2.oblicz();
        }
    }

    @Override
    public String toString() {
        return znak + "("+arg1+", "+arg2+")";
    }
}
