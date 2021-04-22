package obliczenia;


import java.util.Objects;

public class Stala extends Wyrazenie {

    private final Stale val;

    public enum Stale{
        MINUSJEDEN(-1),ZERO(0),JEDEN(1);
        public final int value;
        private Stale(int value) {
            this.value = value;
        }
    }

    public Stala(Stale stale){
        this.val = stale;
    }

    @Override
    public int oblicz() {
        return val.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stala)) return false;
        Stala stala = (Stala) o;
        return val == stala.val;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }

    @Override
    public String toString() {
        return "" + val.value;
    }
}
