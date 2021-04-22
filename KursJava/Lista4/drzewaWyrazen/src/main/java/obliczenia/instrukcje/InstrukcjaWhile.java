package obliczenia.instrukcje;

import obliczenia.Wyrazenie;
import obliczenia.Zmienna;
import obliczenia.operacje2arg.TwoArgCompareOperation;

public class InstrukcjaWhile extends Instrukcja{

    private TwoArgCompareOperation wyr;
    private Instrukcja toDo;
    public InstrukcjaWhile(TwoArgCompareOperation wyr, Instrukcja toDo){
        if (wyr == null || toDo == null) throw new NullPointerException();
        this.wyr = wyr;
        this.toDo = toDo;
    }
    @Override
    public void wykonaj() {
        while(this.wyr.oblicz() == 1){
            toDo.wykonaj();
        }
    }

    @Override
    public String toString() {
        return "while("+wyr.toString()+")\r\n"+
                "\t"+toDo.toString()+"\r\n";
    }
}
