package byog.lab6;

import org.junit.Test;

public class TestMemoryGame {

    public static void testGenerateRandomString() {
        MemoryGame testGenerateRandomString = new MemoryGame(30, 30, 123);
        String x = testGenerateRandomString.generateRandomString(5);
        String y = testGenerateRandomString.generateRandomString(5);
        String j = testGenerateRandomString.generateRandomString(5);
        String jj = testGenerateRandomString.generateRandomString(5);
        String jjj = testGenerateRandomString.generateRandomString(5);
        System.out.println(x);
        System.out.println(y);
        System.out.println(j);
        System.out.println(jj);
        System.out.println(jjj);
    }

    public static void main(String[] args) {
        TestMemoryGame.testGenerateRandomString();
    }

}
