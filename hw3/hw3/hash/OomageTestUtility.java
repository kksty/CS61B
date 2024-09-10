package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] buckets = new int[M];
        int N = oomages.size();
        for (Oomage o : oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            buckets[bucketNum]++;
        }
        int lowerBound = N / 50;
        int upperBound = (int) (N / 2.5);
        for (int count : buckets) {
            if (count < lowerBound || count > upperBound) {
                return false;
            }
        }
        return true;
    }
}
