import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOffByN {
    static CharacterComparator OffBy5 = new OffByN(5);

    @Test
    public void testOffByN() {
        assertTrue(OffBy5.equalChars('a', 'f'));
        assertTrue(OffBy5.equalChars('f', 'a'));
        assertFalse(OffBy5.equalChars('f', 'h'));
    }
}
