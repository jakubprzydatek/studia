package GameEngine;

public class EuropeanBoard extends Board{
    public EuropeanBoard(){
        super();
        generateEmptyBoard(PositionOnBoard.forbiddenPositionsOnEuropean);
    }
}
