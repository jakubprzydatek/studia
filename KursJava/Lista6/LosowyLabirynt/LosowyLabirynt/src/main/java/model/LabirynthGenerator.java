package model;

import java.util.Random;

public class LabirynthGenerator {
    private int size = 20;
    private Cell[][] grid;

    private Cell exit;
    private Cell entry;

    private final Random random = new Random();

    public Cell getEntry() {
        return entry;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void generate(){
        generateEmptyGrid();
        setExitCell();
        System.out.println(exit);
        generatePath(exit);
        int i = countPaths();
        if (i < 60 || i > 80){
            generate();
        }
        i=0;
        while(i<3){
            PositionInGrid randomBorder = getRandomBorder();
            System.out.println("Losowa granica: " + randomBorder);
            generateBranch(grid[randomBorder.getX()][randomBorder.getY()]);
            i++;
        }

    }

    private PositionInGrid getRandomBorder()
    {
        int x, y = 0;
        do{
            x = random.nextInt(20);
            y = random.nextInt(20);
        }
        while(!grid[x][y].isBorder());
        PositionInGrid border = new PositionInGrid(x, y);
        return border;
    }

    private void generateBranch(Cell start){
        if(isAnyDirAvaiable(start)){
            start.setAsNotBorder();
            start.setAsPath();
            Cell currentCell = start;
            while(isAnyDirAvaiable(currentCell)) {
                Cell nextCell = null;
                if (isAnyDirAvaiable(currentCell)){
                    int dir;
                    int attempt=0;
                    do{
                        dir = findDir(currentCell);
                        attempt++;
                    }while (dir == -1 && attempt < 20);
                    nextCell = getNextCell(currentCell, dir);
                    if (nextCell == null) return;

                    nextCell.setAsPath();
                    enclosePrev(currentCell, dir);
                }
                currentCell = nextCell;
            }
        }

    }

    private void generateEmptyGrid(){
        grid = new Cell[size][size];

        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Cell(new Vertex(i*Cell.SIZE, j*Cell.SIZE), new PositionInGrid(i,j));
            }
        }
    }

    private void setExitCell(){
        int i = random.nextInt(20);
        int exitX = i;
        i = random.nextInt(2);
        int exitY = i == 0 ? 0 : size-1;
        grid[exitX][exitY].setAsExit();
        grid[exitX][exitY].setAsPath();
        exit = grid[exitX][exitY];
    }

    private void generatePath(Cell start){
        start.setAsNotBorder();
        Cell currentCell = start;
        while(isAnyDirAvaiable(currentCell)) {
            Cell nextCell = null;
            if (isAnyDirAvaiable(currentCell)){
                int dir;
                int attempt=0;
                do{
                    dir = findDir(currentCell);
                    attempt++;
                }while (dir == -1 && attempt < 20);
                nextCell = getNextCell(currentCell, dir);
                if (nextCell == null) return;

                nextCell.setAsPath();
                enclosePrev(currentCell, dir);
            }
            currentCell = nextCell;
        }
        entry = currentCell;
    }

    private boolean isAnyDirAvaiable(Cell cell){
        int prevX = cell.getPositionInGrid().getX();
        int prevY = cell.getPositionInGrid().getY();
        if (prevX-1 != -1 && !grid[prevX-1][prevY].isBorder() && !grid[prevX-1][prevY].isPath()){
            return true;
        }
        if (prevY-1 != -1 && !grid[prevX][prevY-1].isBorder() && !grid[prevX][prevY-1].isPath()){
            return true;
        }
        if (prevX+1 != size && !grid[prevX+1][prevY].isBorder() && !grid[prevX+1][prevY].isPath()){
            return true;
        }
        if (prevY+1 != size && !grid[prevX][prevY+1].isBorder() && !grid[prevX][prevY+1].isPath()){
            return true;
        }
        return false;
    }

    /**
     * 0 - left, 1 - up, 2 - right, 3 - bottom
     * @param cell
     * @return
     */
    private int findDir(Cell cell){
        int dir = random.nextInt(4);
        int prevX = cell.getPositionInGrid().getX();
        int prevY = cell.getPositionInGrid().getY();

        switch (dir){
            case 0:
                if (prevX-1 != -1 && !grid[prevX-1][prevY].isBorder() && !grid[prevX-1][prevY].isPath()){
                    return 0;
                }
                break;
            case 1:
                if (prevY-1 != -1 && !grid[prevX][prevY-1].isBorder() && !grid[prevX][prevY-1].isPath()){
                    return 1;
                }
                break;
            case 2:
                if (prevX+1 != size && !grid[prevX+1][prevY].isBorder() && !grid[prevX+1][prevY].isPath()){
                    return 2;
                }
                break;
            case 3:
                if (prevY+1 != size && !grid[prevX][prevY+1].isBorder() && !grid[prevX][prevY+1].isPath()){
                    return 3;
                }
                break;
            default:
                return -1;
        }
        return -1;
    }

    private Cell getNextCell(Cell prev, int dir){
        switch (dir){
            case 0:
                return grid[prev.getPositionInGrid().getX()-1][prev.getPositionInGrid().getY()];
            case 1:
                return grid[prev.getPositionInGrid().getX()][prev.getPositionInGrid().getY()-1];
            case 2:
                return grid[prev.getPositionInGrid().getX()+1][prev.getPositionInGrid().getY()];
            case 3:
                return grid[prev.getPositionInGrid().getX()][prev.getPositionInGrid().getY()+1];
        }
        return null;
    }

    private void enclosePrev(Cell cell, int dir){
        int x = cell.getPositionInGrid().getX();
        int y = cell.getPositionInGrid().getY();
        switch (dir){
            case 0:
                if (y -1 != -1 && !grid[x][y-1].isPath() ){
                    grid[x][y-1].setAsBorder();
                }
                if (x+1 != size && !grid[x+1][y].isPath()){
                    grid[x+1][y].setAsBorder();
                }
                if (y+1 != size && !grid[x][y+1].isPath()){
                    grid[x][y+1].setAsBorder();

                }
                break;
            case 1:
                if (x-1 != -1 && !grid[x-1][y].isPath()){
                    grid[x-1][y].setAsBorder();
                }
                if (x+1 != size && !grid[x+1][y].isPath()){
                    grid[x+1][y].setAsBorder();
                }
                if (y+1 != size && !grid[x][y+1].isPath()){
                    grid[x][y+1].setAsBorder();
                }
                break;
            case 2:
                //GÓRA
                if (y -1 != -1 && !grid[x][y-1].isPath()){
                    grid[x][y-1].setAsBorder();
                }
                //LEWO
                if (x-1 != -1 && !grid[x-1][y].isPath()){
                    grid[x-1][y].setAsBorder();
                }
                //DÓł
                if (y+1 != size && !grid[x][y+1].isPath()){
                    grid[x][y+1].setAsBorder();
                }
                break;
            case 3:
                //GÓRA
                if (y -1 != -1 && !grid[x][y-1].isPath()){
                    grid[x][y-1].setAsBorder();
                }
                //LEWO
                if (x-1 != -1 && !grid[x-1][y].isPath()){
                    grid[x-1][y].setAsBorder();
                }
                //PRAWO
                if (x+1 != size && !grid[x+1][y].isPath()){
                    grid[x+1][y].setAsBorder();
                }
        }
        return;
    }

    private int countPaths(){
        int no = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j].isPath()) no++;
            }
        }
        return no;
    }
}

