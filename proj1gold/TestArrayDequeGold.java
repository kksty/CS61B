import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestArrayDequeGold {
    @Test
    public void testArrayDeque() {
        ArrayDequeSolution<Integer> A1 = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> A2 = new StudentArrayDeque<>();
        StringBuilder operations = new StringBuilder();

        for (int i = 0; i < 200; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.3) {
                A1.addLast(i);
                A2.addLast(i);
                operations.append("addLast(").append(i).append(")\n");
            } else if (numberBetweenZeroAndOne >= 0.3 && numberBetweenZeroAndOne < 0.5) {
                A1.addFirst(i);
                A2.addFirst(i);
                operations.append("addFirst(").append(i).append(")\n");
            } else if (!A1.isEmpty() && !A2.isEmpty()) {
                Integer expected;
                Integer actual;
                if (numberBetweenZeroAndOne >= 0.5 && numberBetweenZeroAndOne < 0.7) {
                    expected = A1.removeFirst();
                    actual = A2.removeFirst();
                    operations.append("removeFirst()\n");
                } else {
                    expected = A1.removeLast();
                    actual = A2.removeLast();
                    operations.append("removeLast()\n");
                }
                assertEquals(operations.toString(), expected, actual);
            }
        }
    }
}

