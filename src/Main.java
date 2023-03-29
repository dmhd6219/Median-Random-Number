import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        long[] input = Arrays.stream(bf.readLine().split(" ")).mapToLong(x -> Long.parseLong(x)).toArray();
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

    public static Double[] generateRandomNumbers(long a, long c, long m, long seed, int n) {
        Long[] arrX = new Long[n + 1];
        Double[] arrY = new Double[n];
        arrX[0] = seed;
        for (int i = 1; i < n + 1; i++) {
            long x =  mod((a * arrX[i - 1] + c), m);
            arrX[i] = x;
            Double y = Math.abs((2 * (double)x / m) - 1);
            arrY[i - 1] = y;
        }
        return arrY;
    }

    private static long mod(long x, long y)
    {
        long result = x % y;
        while (result < 0)
        {
            result += y;
        }
        return result;
    }

    public static double getMedian(Double[] arr) {
        Double[] tempArray = arr.clone();
        Sort<Double> sort = new Sort<>();
        sort.quickSort(tempArray, 0, arr.length - 1);

        int index = tempArray.length / 2 - 1;
        return tempArray[index];
    }

    public static int getIndex(Double[] arr, Double elem) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }

}

class Sort<T extends Comparable<T>> {
    public void bucketSort(T[] array) {
        // TODO
    }

    public void quickSort(T[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(T[] arr, int begin, int end) {
        T pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j].compareTo(pivot) < 0) {
                i++;

                T swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        T swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }
}