package obliczenia.operacje2arg;

import obliczenia.Wyrazenie;

import java.util.Objects;

public abstract class TwoArgOperation extends Wyrazenie {
    protected Wyrazenie arg1;
    protected Wyrazenie arg2;
    protected String znak;

    public TwoArgOperation(Wyrazenie arg1, Wyrazenie arg2, String znak){
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.znak = znak;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TwoArgOperation)) return false;
        TwoArgOperation that = (TwoArgOperation) o;
        return Objects.equals(arg1, that.arg1) &&
                Objects.equals(arg2, that.arg2) &&
                Objects.equals(znak, that.znak);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arg1, arg2, znak);
    }

    @Override
    public String toString() {
        return "("+this.arg1 + this.znak + this.arg2+")";
    }
}
