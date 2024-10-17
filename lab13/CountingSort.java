/**
 * Class with 2 ways of doing Counting sort, one naive way and one "better" way
 *
 * @author Akhil Batra, Alexander Hwang
 **/
public class CountingSort {
    /**
     * Counting sort on the given int array. Returns a sorted version of the array.
     * Does not touch original array (non-destructive method).
     * DISCLAIMER: this method does not always work, find a case where it fails
     *
     * @param arr int array that will be sorted
     * @return the sorted array
     */
    public static int[] naiveCountingSort(int[] arr) {
        // find max
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = max > i ? max : i;
        }

        // 收集每个值的所有计数
        int[] counts = new int[max + 1];
        for (int i : arr) {
            counts[i]++;
        }

        // 当我们处理 int 时，我们可以将每个 value count 放入新数组中多次
        int[] sorted = new int[arr.length];
        int k = 0;
        for (int i = 0; i < counts.length; i += 1) {
            for (int j = 0; j < counts[i]; j += 1, k += 1) {
                sorted[k] = i;
            }
        }

        // 但是，下面是使用 Start Position Calculation 的更合适、更通用的计数排序实现
        int[] starts = new int[max + 1];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        int[] sorted2 = new int[arr.length];
        for (int i = 0; i < arr.length; i += 1) {
            int item = arr[i];
            int place = starts[item];
            sorted2[place] = item;
            starts[item] += 1;
        }

        // return the sorted array
        return sorted;
    }

    /**
     * Counting sort on the given int array, must work even with negative numbers.
     * Note, this code does not need to work for ranges of numbers greater
     * than 2 billion.
     * Does not touch original array (non-destructive method).
     *
     * @param arr int array that will be sorted
     */
    public static int[] betterCountingSort(int[] arr) {
        // 找到最大值和最小值
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i : arr) {
            if (i > max) max = i;
            if (i < min) min = i;
        }

        // 计算范围
        int range = max - min + 1;

        // 创建计数数组
        int[] counts = new int[range];
        for (int i : arr) {
            counts[i - min]++;
        }

        // 计算起始位置
        int[] starts = new int[range];
        int pos = 0;
        for (int i = 0; i < range; i++) {
            starts[i] = pos;
            pos += counts[i];
        }

        // 创建排序数组
        int[] sorted = new int[arr.length];
        for (int i : arr) {
            int place = starts[i - min];
            sorted[place] = i;
            starts[i - min]++;
        }

        // 返回排序后的数组
        return sorted;
    }
}
