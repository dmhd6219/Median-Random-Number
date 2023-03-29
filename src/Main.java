import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();
        int a = input[0];
        int c = input[1];
        int m = input[2];
        int seed = input[3];
        int n = Integer.parseInt(bf.readLine());

        double[] nums = generateRandomNumbers(a, c, m, seed, n);
        double median = getMedian(nums);
        int index = getIndex(nums, median);

        System.out.println(index);
    }

    public static double[] generateRandomNumbers(int a, int c, int m, int seed, int n){
        // TODO
        return new double[]{0d};
    }

    public static double getMedian(double[] arr){
        // TODO
        return 0d;
    }

    public static int getIndex(double[] arr, double elem){
        // TODO
        return -1;
    }

}

class Sort<T extends Comparable<T>>{
    public void bucketSort(T[] array){
        // TODO
    }
}