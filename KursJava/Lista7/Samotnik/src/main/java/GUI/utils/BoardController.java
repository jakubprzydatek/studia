package GUI.utils;

import GameEngine.Board;
import GameEngine.Pawn;

public class BoardController {
    private Board board;
    private Pawn currentPawn;
    public BoardController(Board board){
        this.board = board;
        this.currentPawn = null;
    }
}
