package obliczenia.operacje2arg;

import obliczenia.Wyrazenie;

public abstract class TwoArgCompareOperation extends TwoArgOperation{
    public TwoArgCompareOperation(Wyrazenie arg1, Wyrazenie arg2, String znak) {
        super(arg1, arg2, znak);
    }
}
