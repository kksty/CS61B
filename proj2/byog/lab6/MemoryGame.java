package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
            "You got this!", "You're a star!", "Go Bears!",
            "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         * 设置 StdDraw，使其具有 16 x 16 个方格的宽 x 高网格作为其画布 此外，设置比例，使左上角为 （0,0），右下角为 （width， height）
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

//        Initialize random number generator
        rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int x = rand.nextInt(CHARACTERS.length);
            sb.append(CHARACTERS[x]);
            n--;
        }
        return sb.toString();
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen 获取字符串并将其显示在屏幕中央
        //TODO: If game is not over, display relevant game information at the top of the screen 如果游戏未结束，则在屏幕顶部显示相关游戏信息
        StdDraw.clear();
        StdDraw.text(width / 2, width / 2, s);
        String info = "Game!!!";
        StdDraw.text(width / 2, height - 1, info);
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters 以字母显示每个字符，确保在字母之间将屏幕留空
        for (int i = 0; i < letters.length(); i++) {
            drawFrame(String.valueOf(letters.charAt(i)));
            StdDraw.pause(1000);
            StdDraw.clear();
            StdDraw.show();
            StdDraw.pause(500);
        }

    }

    public String solicitNCharsInput(int n) {
        //TODO：读取玩家输入的 n 个字母
        StringBuilder sb = new StringBuilder();
        while (sb.length() < n) {
            if (StdDraw.hasNextKeyTyped()) {
                sb.append(StdDraw.nextKeyTyped());
            }
        }
        return sb.toString();
    }

    public void startGame() {
        //TODO：在游戏开始前设置任何相关变量
        int n = 0;
        while (true) {
            n += 1; //从第 1 轮开始游戏
            String round = "Round: " + n;
            drawFrame(round); //显示消息 “Round：”，后跟屏幕中央的整数
            String randomStrings = generateRandomString(n); //生成长度等于当前轮次的随机字符串
            flashSequence(randomStrings);//一次显示一个字母的随机字符串
            String resultString = solicitNCharsInput(n);//等待播放器键入与目标字符串长度相同的字符串
            if (!randomStrings.equals(resultString)) {
                System.out.println("Game Over! You made it to round:" + n);
                break;
            } else {
                drawFrame("Correct! Well done!");
                StdDraw.pause(1000);
            }
        }
        //TODO：建立游戏循环
    }

}
