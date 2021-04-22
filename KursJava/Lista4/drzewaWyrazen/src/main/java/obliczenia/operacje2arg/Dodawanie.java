package obliczenia.operacje2arg;

import obliczenia.Wyrazenie;

import java.util.Objects;

public class Dodawanie extends TwoArgOperation {

    public Dodawanie(Wyrazenie arg1, Wyrazenie arg2) {
        super(arg1, arg2, "+");
    }

    @Override
    public int oblicz() {
        return arg1.oblicz() + arg2.oblicz();
    }

}
