import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestArrayDequeGold {
    @Test
    public void testArrayDeque() {
        ArrayDequeSolution<Integer> A1 = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> A2 = new StudentArrayDeque<>();

        for (int i = 0; i < 200; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.3) {
                A1.addLast(i);
                A2.addLast(i);
            } else if (numberBetweenZeroAndOne >= 0.3 && numberBetweenZeroAndOne < 0.5) {
                A1.addFirst(i);
                A2.addFirst(i);
            } else if (!A1.isEmpty() && !A2.isEmpty()) {
                if (numberBetweenZeroAndOne >= 0.5 && numberBetweenZeroAndOne < 0.7) {
                    Integer expected = A1.removeFirst();
                    Integer actual = A2.removeFirst();
                    assertEquals("发生了错误！\n预期removeFirst：" + expected + "\n但是实际removeFirst：" + actual, expected, actual);
                } else {
                    Integer expected = A1.removeLast();
                    Integer actual = A2.removeLast();
                    assertEquals("发生了错误！\n预期removeLast：" + expected + "\n但是实际removeLast：" + actual, expected, actual);
                }
            }
        }
    }
}

