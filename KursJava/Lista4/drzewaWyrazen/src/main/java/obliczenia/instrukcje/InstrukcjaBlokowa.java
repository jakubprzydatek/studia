package obliczenia.instrukcje;

import obliczenia.Zmienna;

import java.util.ArrayList;

public class InstrukcjaBlokowa extends Instrukcja{
    private Instrukcja[] instrukcje;
    private ArrayList<String> zmienneWBloku = new ArrayList<>();
    public InstrukcjaBlokowa(Instrukcja... instrukcje){
        if(!checkIfNotNull(instrukcje)){
            throw new NullPointerException();
        }
        this.instrukcje=instrukcje;
    }
    @Override
    public void wykonaj() {
        for(Instrukcja i : instrukcje){
            if(i instanceof DeklaracjaZmiennej)
            {
                zmienneWBloku.add(((DeklaracjaZmiennej) i).getNazwaZmiennej());
            }
            i.wykonaj();
        }
        removeAllVariables();
    }
    
    private void removeAllVariables(){
        for(String zmienna : zmienneWBloku){
            Zmienna.zbiorZmiennych.remove(zmienna);
        }
    }

    @Override
    public String toString() {
        StringBuilder generatedString= new StringBuilder();
        for(Instrukcja i : instrukcje){
            generatedString.append("\t"+i.toString());
        }
        return "{\r\n"+generatedString+"}";
    }

    private boolean checkIfNotNull(Instrukcja[] instrukcjeDoSprawdzenia){
        for(Instrukcja inst : instrukcjeDoSprawdzenia){
            if(inst == null) return false;
        }
        return true;
    }
}
