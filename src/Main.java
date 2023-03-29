import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * The Main class.
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
    public static Double[] generateRandomNumbers(final long a, final long c, final long m, final long seed, final int n) {
        Long[] arrX = new Long[n + 1];
        Double[] arrY = new Double[n];
        arrX[0] = seed;
        for (int i = 1; i < n + 1; i++) {
            long x = mod((a * arrX[i - 1] + c), m);
            arrX[i] = x;
            Double y = Math.abs((2 * (double) x / m) - 1);
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
        sort.quickSort(tempArray, 0, arr.length - 1);

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

/**
 * The Sorting class, which contains BucketSort and QuickSort.
 *
 * @param <T> the type parameter for QuickSort
 */
class Sort<T extends Comparable<T>> {
    /**
     * Bucket sort algorithm.
     *
     * @param array the array
     * @return sorted array
     */
    public Double[] bucketSort(Double[] array) {
        int numberOfBuckets = (int) Math.sqrt(array.length);
        ArrayList<ArrayList<Double>> buckets = new ArrayList<>();
        for (int i = 0; i < numberOfBuckets; i++) {
            buckets.add(new ArrayList<>());
        }
        Double max = findMax(array);

        for (Double i : array) {
            buckets.get(hash(i, max, numberOfBuckets)).add(i);
        }

        Comparator<Double> comparator = Comparator.naturalOrder();

        for (List<Double> bucket : buckets) {
            bucket.sort(comparator);
        }

        Double[] sortedArray = new Double[array.length];
        int size = 0;

        for (List<Double> bucket : buckets) {
            for (double e : bucket) {
                sortedArray[size] = e;
                size++;
            }
        }

        return sortedArray;

    }

    /**
     * hash function to distribute each element array into its relevant bucket
     *
     * @param elem            element to distribute
     * @param max             maximum of array
     * @param numberOfBuckets number of buckets
     * @return hashed value
     */
    private int hash(Double elem, Double max, int numberOfBuckets) {
        return (int) (elem / max * (numberOfBuckets - 1));
    }

    /**
     * Finds maximum value in array of doubles.
     *
     * @param array array
     * @return
     */
    private Double findMax(Double[] array) {
        double m = Double.MIN_VALUE;
        for (double i : array) {
            m = Math.max(i, m);
        }
        return m;
    }


    /**
     * Quick sort algorithm.
     *
     * @param arr   array to sort
     * @param begin index of start of segment to sort
     * @param end   index of end of segment to sort
     */
    public void quickSort(T[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    /**
     * Takes last element as pivot, then checks each element and swaps it before the pivot if its value is smaller.
     *
     * @param arr   array to sort
     * @param begin index of start of segment to sort
     * @param end   index of end of segment to sort
     * @return
     */
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