package obliczenia.instrukcje;

import obliczenia.Wyrazenie;
import obliczenia.operacje2arg.*;

public class InstrukcjaIf extends Instrukcja{

    private TwoArgCompareOperation wyr;
    private Instrukcja toDo;

    public InstrukcjaIf(TwoArgCompareOperation wyr, Instrukcja toDo){
        if(wyr == null || toDo == null) throw new NullPointerException();
        this.wyr=wyr;
        this.toDo=toDo;

    }
    @Override
    public void wykonaj() {
        if(this.wyr.oblicz() == 1){
            toDo.wykonaj();
        }
    }

    @Override
    public String toString() {
        return "if("+wyr.toString()+") "+toDo.toString()+"\r\n";
    }
}
