import java.util.Arrays;

public class SpiralUtils {
    public static boolean isPrime(int num)
    {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


    public static void fillRowsWithZeros(int[][] a, int rows, int cols) {
        for(int i = 0; i< rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                a[i][j] = 0;
            }
        }
    }

    public static char getSpaceOrStar(int n)
    {
        if(isPrime(n))
        {
            return '*';
        }
        else{
            return ' ';
        }
    }
}
