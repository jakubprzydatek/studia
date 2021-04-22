package GameEngine;

import java.util.ArrayList;
import java.util.Objects;

public class PositionOnBoard {
    public static ArrayList<PositionOnBoard> forbiddenPositionsOnBritish;
    public static ArrayList<PositionOnBoard> forbiddenPositionsOnEuropean;

    public static void fillIllegalBritishPosition(){
        forbiddenPositionsOnBritish = new ArrayList<PositionOnBoard>();
        for(int y=0;y<7;y++){
            for(int x=0;x<7;x++){
                if(x<2 || x>4){
                    if(y<2 || y>4){
                        forbiddenPositionsOnBritish.add(new PositionOnBoard(x, y));
                    }
                }
            }
        }
    }

    public static void fillIllegalEuropeanPosition(){
        forbiddenPositionsOnEuropean = new ArrayList<PositionOnBoard>(forbiddenPositionsOnBritish);
        PositionOnBoard pos1 = new PositionOnBoard(1,1);
        PositionOnBoard pos2 = new PositionOnBoard(5,1);
        PositionOnBoard pos3 = new PositionOnBoard(1,5);
        PositionOnBoard pos4 = new PositionOnBoard(5,5);
        forbiddenPositionsOnEuropean.remove(pos1);
        forbiddenPositionsOnEuropean.remove(pos2);
        forbiddenPositionsOnEuropean.remove(pos3);
        forbiddenPositionsOnEuropean.remove(pos4);

    }

    public static boolean checkIfLegalPosition(int i, int j, String type){
        if(i<0||j<0||i>6||j>6) {
            return false;
        }
        PositionOnBoard pos = new PositionOnBoard(i, j);
        if(type.equals("eu")){
            if(forbiddenPositionsOnEuropean.contains(pos)) return false;
        }else{
            if(forbiddenPositionsOnBritish.contains(pos)) return false;
        }
        return true;
    }

    private int i, j;
    public PositionOnBoard(int i, int j){
        this.i = i;
        this.j =j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    @Override
    public String toString() {
        return "("+ getI()+", "+ getJ()+")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PositionOnBoard)) return false;
        PositionOnBoard that = (PositionOnBoard) o;
        return getI() == that.getI() &&
                getJ() == that.getJ();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getI(), getJ());
    }
}
