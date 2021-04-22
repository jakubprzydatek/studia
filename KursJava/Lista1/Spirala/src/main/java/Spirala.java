import java.util.SortedMap;



public class Spirala {

    private int[][] spirala;
    private char[][] filledSpiral;
    private int size = 0;
    private int currentRow;
    private int currentColumn;
    private int currentNumber;
    private MoveDirections currentDirection;
    private boolean doIt;

    public Spirala(int n)
    {
        this.size = n;
        if(n < 2 || n > 200)
        {
            throw new IllegalArgumentException("Dozwolony zakres n to 2-200");
        }

        this.spirala = new int[n][n];
        SpiralUtils.fillRowsWithZeros(spirala, n, n);
        this.filledSpiral = new char[n][n];
        this.currentNumber = 1;
    }

    public void startSpiral(){
        doIt = true;
        selectStart();
        fillFirstCells();
        while(doIt){
            fillNextCell();
        }

    }

    private void fillNextCell(){
        this.currentNumber += 1;
        selectDirection();
        if(currentDirection == MoveDirections.LEFT)
        {
            fillLeftCell();
        }
        if(currentDirection == MoveDirections.RIGHT)
        {
            fillRightCell();
        }
        if(currentDirection == MoveDirections.UP)
        {

            fillUpCell();
        }
        if(currentDirection == MoveDirections.DOWN)
        {
            fillDownCell();
        }
    }

    private void selectDirection()
    {
        if(currentDirection == MoveDirections.LEFT)
        {
            if(isFree(spirala[currentRow + 1][currentColumn]))
            {
                currentDirection = MoveDirections.DOWN;
            }
        }

        if(currentDirection == MoveDirections.RIGHT)
        {
            if(isFree(spirala[currentRow - 1][currentColumn]))
            {
                currentDirection = MoveDirections.UP;
            }
        }

        if(currentDirection == MoveDirections.DOWN)
        {
            if(isFree(spirala[currentRow][currentColumn + 1]))
            {
                currentDirection = MoveDirections.RIGHT;
            }
        }

        if(currentDirection == MoveDirections.UP)
        {
            if(isFree(spirala[currentRow][currentColumn - 1]))
            {
                currentDirection = MoveDirections.LEFT;
            }
        }
    }

    private void fillFirstCells()
    {
        this.spirala[currentRow][currentColumn] = currentNumber;
        this.filledSpiral[currentRow][currentColumn] = SpiralUtils.getSpaceOrStar(currentNumber);
        currentNumber += 1;
        currentColumn += 1;
        this.spirala[currentRow][currentColumn] = currentNumber;
        this.filledSpiral[currentRow][currentColumn] = SpiralUtils.getSpaceOrStar(currentNumber);
        currentNumber += 1;
        currentRow -= 1;
        this.spirala[currentRow][currentColumn] = currentNumber;
        this.filledSpiral[currentRow][currentColumn] = SpiralUtils.getSpaceOrStar(currentNumber);
        currentDirection = MoveDirections.LEFT;
    }

    private void fillLeftCell()
    {
        currentColumn -= 1;
        fillCell();
    }

    private void fillRightCell()
    {
        currentColumn += 1;
        fillCell();
    }

    private void fillUpCell()
    {
        currentRow -= 1;
        fillCell();
    }

    private void fillDownCell()
    {
        currentRow += 1;
        fillCell();
    }

    private void fillCell()
    {
        if(isOutOfSize(currentColumn, currentRow))
        {
            doIt = false;
        }else
        {
            this.spirala[currentRow][currentColumn] = currentNumber;
            this.filledSpiral[currentRow][currentColumn] = SpiralUtils.getSpaceOrStar(currentNumber);
        }
    }

    public void selectStart()
    {
        int startW, startK;
        if(size % 2 == 1)
        {
            startW = size/2;
            startK = startW;
        }
        else
        {
            startW = size/2;
            startK = startW - 1;
        }
        this.currentRow = startW;
        this.currentColumn = startK;
    }

    public boolean isFree(int number)
    {
        return number == 0;
    }

    public boolean isOutOfSize(int x, int y)
    {
        if((x < 0) || (x >= this.size))
        {
            return true;
        }
        if((y < 0) || (y >= size))
        {
            return true;
        }
        return false;
    }
    public void print()
    {
        for(int i = 0; i< size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                System.out.printf("%6d",spirala[i][j]);
                //System.out.print(spirala[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printFilledSpiral()
    {
        for(int i = 0; i< size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                System.out.printf("%6c",filledSpiral[i][j]);
            }
            System.out.println();
        }
    }
}
