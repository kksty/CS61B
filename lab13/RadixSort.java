public class RadixSort {
    /**
     * 对传入的字符串数组进行LSD基数排序，限制如下：
     * 数组只能包含ASCII字符串（1字节字符序列）
     * 排序是稳定且非破坏性的
     * 字符串可以是可变长度的（所有字符串不受1长度限制）
     *
     * @param asciis 需要排序的字符串数组
     * @return 排序后的字符串数组
     */
    public static String[] sort(String[] asciis) {
        // 找到最长字符串的长度
        int maxLength = 0;
        for (String s : asciis) {
            if (s.length() > maxLength) {
                maxLength = s.length();
            }
        }

        // 从最低有效位开始，对每一位进行计数排序
        String[] sortedArray = asciis.clone();
        for (int i = maxLength - 1; i >= 0; i--) {
            sortHelperLSD(sortedArray, i);
        }

        return sortedArray;
    }

    /**
     * LSD辅助方法，根据特定索引的字符对字符串数组进行破坏性计数排序。
     *
     * @param asciis 输入的字符串数组
     * @param index  要排序的字符位置
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        int[] count = new int[257]; // 256 ASCII characters + 1 for padding
        String[] output = new String[asciis.length];

        // 统计每个字符的出现次数
        for (String s : asciis) {
            int charIndex = index < s.length() ? (int)s.charAt(index) + 1 : 0;
            count[charIndex]++;
        }

        // 计算每个字符的起始位置
        for (int i = 1; i < 257; i++) {
            count[i] += count[i - 1];
        }

        // 将字符串放置在正确的位置
        for (int i = asciis.length - 1; i >= 0; i--) {
            int charIndex = index < asciis[i].length() ? (int)asciis[i].charAt(index) + 1 : 0;
            output[count[charIndex] - 1] = asciis[i];
            count[charIndex]--;
        }

        // 复制回原数组
        System.arraycopy(output, 0, asciis, 0, asciis.length);
    }
}