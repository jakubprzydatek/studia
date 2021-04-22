package obliczenia;

import java.util.Objects;

public class Liczba extends Wyrazenie {
    private final int wartosc;
    public Liczba(int wartosc){
        this.wartosc = wartosc;
    }

    public int oblicz() {
        return this.wartosc;
    }


    @Override
    public String toString(){
        return Integer.toString(this.wartosc);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Liczba)) return false;
        Liczba liczba = (Liczba) o;
        return wartosc == liczba.wartosc;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wartosc);
    }
}
