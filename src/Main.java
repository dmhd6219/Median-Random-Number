import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * The Main class.
 *
 * @author Sviatoslav Sviatkin
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        long[] input = Arrays.stream(bf.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long a = input[0];
        long c = input[1];
        long m = input[2];
        long seed = input[3];
        int n = Integer.parseInt(bf.readLine());

        Double[] nums = generateRandomNumbers(a, c, m, seed, n);
        double median = getMedian(nums);
        int index = getIndex(nums, median);

        System.out.println(index + 1);
    }


    /**
     * Generates numbers randomly.
     *
     * @param a    constant a
     * @param c    constant c
     * @param m    constant m
     * @param seed constant seed
     * @param n    amount of numbers to generate
     * @return array with randomly generated numbers
     */
    public static Double[] generateRandomNumbers(long a, long c, long m, long seed, int n) {
        Long[] arrX = new Long[n + 1];
        Double[] arrY = new Double[n];
        arrX[0] = seed;
        for (int i = 1; i < n + 1; i++) {
            long x = mod((a * arrX[i - 1] + c), m);
            arrX[i] = x;
            double y = Math.abs((2 * (double) x / m) - 1);
            arrY[i - 1] = y;
        }
        return arrY;
    }

    /**
     * Returns the remainder or the signed remainder of the division
     *
     * @param x first number
     * @param y second number
     * @return remainder or the signed remainder of the division
     */
    private static long mod(long x, long y) {
        long result = x % y;
        while (result < 0) {
            result += y;
        }
        return result;
    }

    /**
     * Gets median of array of doubles.
     *
     * @param arr the array
     * @return the median
     */
    public static double getMedian(Double[] arr) {
        Double[] tempArray = arr.clone();
        Sort<Double> sort = new Sort<>();

        tempArray = sort.bucketSort(tempArray);

        int index = tempArray.length / 2 - 1;
        return tempArray[index];
    }

    /**
     * Gets index of element in array of doubles.
     *
     * @param arr  the array
     * @param elem the element
     * @return the index
     */
    public static int getIndex(Double[] arr, Double elem) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }

}

