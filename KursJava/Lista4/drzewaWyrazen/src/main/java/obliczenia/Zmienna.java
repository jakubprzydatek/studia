package obliczenia;


import java.util.HashMap;
import java.util.Objects;


public class Zmienna extends Wyrazenie {

    public static final HashMap<String, Integer> zbiorZmiennych = new HashMap<>();
    private final String nazwaZmiennej;
    public Zmienna(String nazwaZmiennej){
        this.nazwaZmiennej = nazwaZmiennej;

    }
    @Override
    public int oblicz(){
        int valToReturn = 0;
            valToReturn = Zmienna.zbiorZmiennych.get(nazwaZmiennej);

            return valToReturn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Zmienna)) return false;
        Zmienna zmienna = (Zmienna) o;
        return Objects.equals(nazwaZmiennej, zmienna.nazwaZmiennej);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazwaZmiennej);
    }

    @Override
    public String toString() {
        return nazwaZmiennej;
    }
}
