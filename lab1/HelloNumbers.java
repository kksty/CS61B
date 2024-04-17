package week1.HW;

public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0, n = 0;
        while (x < 10) {
            n = x + n;
            System.out.print(n + " ");
            x = x + 1;
        }
    }
}