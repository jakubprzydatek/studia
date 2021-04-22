package obliczenia.operacje1arg;

import obliczenia.Wyrazenie;

import java.util.Objects;

public class ZmianaZnaku extends OneArgOperation{

    public ZmianaZnaku(Wyrazenie arg) {
        super(arg, "-");
    }

    @Override
    public int oblicz() {
        return -arg.oblicz();
    }


}
