package obliczenia.operacje2arg;

import obliczenia.Wyrazenie;

public class Wieksze extends TwoArgCompareOperation{
    public Wieksze(Wyrazenie arg1, Wyrazenie arg2) {
        super(arg1, arg2, ">");
    }

    @Override
    public int oblicz() {
        if(this.arg1.oblicz() > this.arg2.oblicz()){
            return 1;
        }else{
            return 0;
        }

    }
}
