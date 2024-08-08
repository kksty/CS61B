import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FlikTest {
    @Test
    public void testIsSameNumber() {
        assertTrue(Flik.isSameNumber(127, 127));
        assertTrue(Flik.isSameNumber(128, 128));
    }
}
