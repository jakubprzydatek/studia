package obliczenia.operacje2arg;

import obliczenia.Wyrazenie;

public class LogarytmDyskretny extends TwoArgOperation{
    public LogarytmDyskretny(Wyrazenie podstawaLogarytmu, Wyrazenie liczbaLogarytmowana) {
        super(podstawaLogarytmu, liczbaLogarytmowana, "log");
    }

    @Override
    public int oblicz() {
        return LogarytmDyskretny.log(arg2.oblicz(), arg1.oblicz());
    }

    public static int log(int x, int podstawa)
    {
        return (int) (Math.log(x) / Math.log(podstawa));
    }

    @Override
    public String toString() {
        return "("+znak+arg1+"("+arg2+"))";
    }
}
