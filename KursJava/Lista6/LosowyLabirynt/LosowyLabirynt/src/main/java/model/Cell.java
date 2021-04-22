package model;

public class Cell {
    public static final int SIZE = 30;
    private Vertex upperLeftVertex;

    private PositionInGrid positionInGrid;

    private boolean isExit = false;
    private boolean isEnter = false;
    private boolean isPath = false;
    private boolean isBorder = false;


    public Cell( Vertex upperLeft, PositionInGrid positionInGrid) {
        this.upperLeftVertex = upperLeft;
        this.positionInGrid = positionInGrid;
    }

    public Vertex getUpperLeftVertex() {
        return upperLeftVertex;
    }

    public void setAsExit(){
        isExit = true;
    }


    public boolean isExit() {
        return isExit;
    }

    public boolean isEnter() {
        return isEnter;
    }

    public PositionInGrid getPositionInGrid() {
        return positionInGrid;
    }

    public boolean isBorder() {
        return isBorder;
    }

    public boolean isPath() {
        return isPath;
    }

    public void setAsPath(){
        isPath = true;
    }

    public void setAsBorder(){
        isBorder = true;
    }

    public void setAsNotBorder(){
        isBorder = false;
    }


    @Override
    public String toString() {
        return positionInGrid.toString();
    }
}
