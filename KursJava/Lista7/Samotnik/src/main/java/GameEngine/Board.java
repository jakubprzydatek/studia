package GameEngine;

import java.util.ArrayList;

public abstract class Board {
    protected Pawn[][] board;
    private int availableMoves;
    public Board(){
        this.availableMoves=0;
    }

    public void generateEmptyBoard(ArrayList<PositionOnBoard> forbiddenPositions){
        board = new Pawn[7][7];
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                board[i][j] = new Pawn(new PositionOnBoard(i, j));
                if(!forbiddenPositions.contains(new PositionOnBoard(i, j))){
                    board[i][j].setPawn(true);
                }
            }
        }
        //System.out.println(this);
    }

    public Pawn getPawn(int j, int i){
        return board[j][i];
    }

    public int countAvailableBoardMoves(String gameType){
        this.availableMoves = 0;
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                if(!board[j][i].isPawn()) continue;
                if(board[j][i].isBeaten() && board[j][i].isPawn()) continue;
                availableMoves+=countAvailablePawnMoves(board[j][i], gameType);
            }
        }
        return availableMoves;
    }

    public int countAvailablePawnMoves(Pawn pawn, String gameType){
        int sum = 0;
        if(canMoveUp(pawn, gameType)) sum+=1;
        if(canMoveDown(pawn, gameType)) sum+=1;
        if(canMoveLeft(pawn, gameType)) sum+=1;
        if(canMoveRight(pawn, gameType)) sum+=1;
        return sum;
    }
    public boolean canMoveUp(Pawn pawn, String gameType){
        if(PositionOnBoard.checkIfLegalPosition(pawn.getPosition().getI(), pawn.getPosition().getJ()+2, gameType)){
            return !(board[pawn.getPosition().getI()][pawn.getPosition().getJ() + 1]).isBeaten() && (board[pawn.getPosition().getI()][pawn.getPosition().getJ() + 2]).isBeaten();
        }
        return false;
    }

    public boolean canMoveDown(Pawn pawn, String gameType){
        if(PositionOnBoard.checkIfLegalPosition(pawn.getPosition().getI(), pawn.getPosition().getJ()-2, gameType)){
            return !(board[pawn.getPosition().getI()][pawn.getPosition().getJ() - 1]).isBeaten() && (board[pawn.getPosition().getI()][pawn.getPosition().getJ() - 2]).isBeaten();
        }
        return false;
    }
    public boolean canMoveLeft(Pawn pawn, String gameType){
        if(PositionOnBoard.checkIfLegalPosition(pawn.getPosition().getI()-2, pawn.getPosition().getJ(), gameType)){
            return !(board[pawn.getPosition().getI()-1][pawn.getPosition().getJ()]).isBeaten() && (board[pawn.getPosition().getI()-2][pawn.getPosition().getJ()]).isBeaten();
        }
        return false;
    }
    public boolean canMoveRight(Pawn pawn, String gameType){
        if(PositionOnBoard.checkIfLegalPosition(pawn.getPosition().getI()+2, pawn.getPosition().getJ(), gameType)){
            return !(board[pawn.getPosition().getI()+1][pawn.getPosition().getJ()]).isBeaten() && (board[pawn.getPosition().getI()+2][pawn.getPosition().getJ()]).isBeaten();
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder toReturn= new StringBuilder();
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                toReturn.append(board[j][i]);
            }
            toReturn.append("\n");
        }
        return toReturn.toString();
    }
}
