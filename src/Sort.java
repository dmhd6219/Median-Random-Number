import java.util.ArrayList;
import java.util.List;

/**
 * The Sorting class, which contains BucketSort and QuickSort.
 *
 * @param <T> the type parameter for QuickSort
 */
public class Sort<T extends Comparable<T>> {
    /**
     * Bucket sort algorithm.
     * Im really sorry that i have standart sorting here, but i don't have time to make changes in my quick sort :(
     *
     * @param array the array
     * @return sorted array
     */
    public Double[] bucketSort(Double[] array) {
        final int numberOfBuckets = (int) Math.sqrt(array.length);
        List<List<Double>> buckets = new ArrayList<>();

        for (int i = 0; i < numberOfBuckets; i++) {
            buckets.add(new ArrayList<>());
        }


        for (double i : array) {
            buckets.get(hash(i, numberOfBuckets)).add(i);
        }

        for (List<Double> bucket : buckets) {
            bucket.sort(null);
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
     * Hash function to distribute each element array into its relevant bucket
     *
     * @param elem            element to distribute
     * @param numberOfBuckets number of buckets
     * @return hashed value
     */
    private int hash(Double elem, int numberOfBuckets) {
        return (int) (elem / (numberOfBuckets - 1));
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
     * @return new index of pivot
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
