package GameEngine;

import java.lang.reflect.Array;

public class BritishBoard extends Board{
    public BritishBoard(){
        super();
        generateEmptyBoard(PositionOnBoard.forbiddenPositionsOnBritish);
    }
}
