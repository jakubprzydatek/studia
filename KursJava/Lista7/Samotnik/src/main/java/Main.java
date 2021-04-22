import GUI.Frame;
import GameEngine.BritishBoard;
import GameEngine.EuropeanBoard;
import GameEngine.PositionOnBoard;

public class Main {
    public static void main(String[] args) {
        PositionOnBoard.fillIllegalBritishPosition();
        PositionOnBoard.fillIllegalEuropeanPosition();
        //System.out.println(PositionOnBoard.forbiddenPositionsOnBritish);
        //System.out.println(PositionOnBoard.forbiddenPositionsOnEuropean);
        BritishBoard boardGB = new BritishBoard();
        System.out.println(" ");
        EuropeanBoard boardEU = new EuropeanBoard();

        new Frame();

    }
}
