package obliczenia.instrukcje;

import obliczenia.operacje2arg.TwoArgCompareOperation;

public class InstrukcjaIfElse extends Instrukcja{

    private TwoArgCompareOperation wyr;
    private Instrukcja toDo;
    private Instrukcja toDoElse;

    public InstrukcjaIfElse(TwoArgCompareOperation wyr, Instrukcja toDo, Instrukcja toDoElse){
        if(wyr == null || toDo == null || toDoElse == null) throw new NullPointerException();
        this.wyr=wyr;
        this.toDo=toDo;
        this.toDoElse=toDoElse;

    }
    @Override
    public void wykonaj() {
        if(this.wyr.oblicz() == 1){
            toDo.wykonaj();
        }else{
            toDoElse.wykonaj();
        }
    }

    @Override
    public String toString() {
        return "if("+wyr.toString()+") "
                +toDo.toString()+"\telse "+
                toDoElse.toString()+"\r\n";
    }
}
