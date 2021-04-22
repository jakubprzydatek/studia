package obliczenia.instrukcje;

import obliczenia.operacje2arg.TwoArgCompareOperation;

public class InstrukcjaDoWhile extends Instrukcja{

    private TwoArgCompareOperation wyr;
    private Instrukcja toDo;
    public InstrukcjaDoWhile(TwoArgCompareOperation wyr, Instrukcja toDo){
        if(wyr == null || toDo == null)  throw new NullPointerException();
        this.wyr = wyr;
        this.toDo = toDo;
    }
    @Override
    public void wykonaj() {
        toDo.wykonaj();
        while(this.wyr.oblicz() == 1){
            toDo.wykonaj();
        }
    }

    @Override
    public String toString() {
        return "do{ \r\n" +toDo.toString()+"}while ("+wyr.toString()+")\r\n";
    }
}
