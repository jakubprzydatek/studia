package GameEngine;

public class Pawn {
    private PositionOnBoard position;
    private boolean isPawn = false;
    private boolean isActive = false;
    private boolean isBeaten = false;
    public Pawn(PositionOnBoard pos){
        this.position = pos;
    }

    public PositionOnBoard getPosition() {
        return position;
    }

    public void setPosition(PositionOnBoard position) {
        this.position = position;
    }

    public boolean isPawn() {
        return isPawn;
    }

    public void setPawn(boolean pawn) {
        isPawn = pawn;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isBeaten() {
        return isBeaten;
    }

    public void setBeaten(boolean beaten) {
        isBeaten = beaten;
    }

    @Override
    public String toString() {
        if(isActive) return "*";
        if(isBeaten) return "-";
        if(isPawn) return ".";
        return " ";
    }
}
