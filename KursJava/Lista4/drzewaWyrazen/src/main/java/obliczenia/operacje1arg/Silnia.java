package obliczenia.operacje1arg;

import obliczenia.Wyrazenie;
import obliczenia.operacje2arg.Dodawanie;

import java.util.Objects;

public class Silnia extends OneArgOperation {
    public Silnia(Wyrazenie arg) {
        super(arg, "!");
    }

    @Override
    public int oblicz() {
        return Silnia.factor(arg.oblicz());
    }


    private static int factor(int i)
    {
        if (i < 1)
            return 1;
        else
            return i * factor(i - 1);
    }

}
