package obliczenia.operacje2arg;

import obliczenia.Wyrazenie;

public class Potegowanie extends TwoArgOperation{
    public Potegowanie(Wyrazenie arg1, Wyrazenie arg2) {
        super(arg1, arg2, "^");
    }

    @Override
    public int oblicz() {
        return (int) Math.pow(arg1.oblicz(), arg2.oblicz());
    }
}
