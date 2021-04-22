package obliczenia.operacje1arg;

import obliczenia.Wyrazenie;

import java.util.Objects;

public abstract class OneArgOperation extends Wyrazenie {
    protected Wyrazenie arg;
    protected String znak;

    public OneArgOperation(Wyrazenie arg, String znak){
        this.arg = arg;
        this.znak = znak;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OneArgOperation)) return false;
        OneArgOperation that = (OneArgOperation) o;
        return Objects.equals(arg, that.arg) &&
                Objects.equals(znak, that.znak);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arg, znak);
    }

    @Override
    public String toString(){
        return "("+this.znak + this.arg+")";
    }
}
