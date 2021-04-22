package obliczenia.operacje2arg;

import obliczenia.Wyrazenie;

public class Rowne extends TwoArgCompareOperation{
    public Rowne(Wyrazenie arg1, Wyrazenie arg2) {
        super(arg1, arg2, "==");
    }

    @Override
    public int oblicz() {
        if(arg1.oblicz() == arg2.oblicz()){
            return 1;
        }else{
            return 0;
        }
    }


}
