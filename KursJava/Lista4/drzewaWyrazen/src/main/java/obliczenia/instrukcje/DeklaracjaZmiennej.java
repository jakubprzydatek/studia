package obliczenia.instrukcje;


import obliczenia.Zmienna;

public class DeklaracjaZmiennej extends Instrukcja{
    private String nazwaZmiennej;
    public DeklaracjaZmiennej(String nazwaZmiennej){
        if(nazwaZmiennej == null) throw new NullPointerException();
        this.nazwaZmiennej = nazwaZmiennej;
    }
    @Override
    public void wykonaj() {
        if(Zmienna.zbiorZmiennych.containsKey(nazwaZmiennej)){
            System.out.println("Ta zmienna została już zadeklarowana");
        }else{
            Zmienna.zbiorZmiennych.put(nazwaZmiennej, 0);
        }
    }

    public String getNazwaZmiennej(){
        return this.nazwaZmiennej;
    }

    @Override
    public String toString() {
        return "var "+this.nazwaZmiennej+"; \r\n";
    }
}
